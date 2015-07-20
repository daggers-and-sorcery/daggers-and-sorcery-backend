package com.swordssorcery.server.game.attribute.calc;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.AttributeCalculator;
import com.swordssorcery.server.game.attribute.data.GeneralAttributeData;
import com.swordssorcery.server.game.attribute.modifier.AttributeModifierCalculator;
import com.swordssorcery.server.game.attribute.type.GeneralAttribute;
import com.swordssorcery.server.game.attribute.type.SkillAttribute;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralAttributeCalculator implements AttributeCalculator {

    private static int STARTING_SKILL_LEVEL = 1;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private AttributeModifierCalculator attributeModifierCalculator;

    @Override
    public GeneralAttributeData calculateAttributeValue(UserDatabaseEntity user, Attribute attribute) {
        if (!(attribute instanceof GeneralAttribute)) {
            throw new IllegalArgumentException("The attribute must be an instance of GeneralAttribute.");
        }

        GeneralAttribute localAttribute = (GeneralAttribute) attribute;

        GeneralAttributeData.GeneralAttributeDataBuilder attributeDataBuilder = new GeneralAttributeData.GeneralAttributeDataBuilder(localAttribute);

        attributeDataBuilder.setActual(globalAttributeCalculator.calculateActualValue(user, localAttribute));
        attributeDataBuilder.setMaximum(globalAttributeCalculator.calculateMaximumValue(user, localAttribute));
        attributeDataBuilder.setAttributeModifierDataArray(attributeModifierCalculator.calculateModifierData(user, localAttribute));
        attributeDataBuilder.setPointsToNextLevel(calculatePointsToAttributeLevel(user, attribute));

        return attributeDataBuilder.build();
    }

    public int calculatePointsToAttributeLevel(UserDatabaseEntity user, Attribute attribute) {
        int result = 0;

        for (SkillAttribute skill : SkillAttribute.values()) {
            if(skill.getIncrementedAttribute() == attribute) {
                result += user.getSkills().getSkillLevel(skill) - STARTING_SKILL_LEVEL;
            }
        }

        return result % 10;
    }

    public int calculatePointsBonusBySkills(UserDatabaseEntity user, Attribute attribute) {
        int result = 0;

        for (SkillAttribute skill : SkillAttribute.values()) {
            if(skill.getIncrementedAttribute() == attribute) {
                result += user.getSkills().getSkillLevel(skill) - STARTING_SKILL_LEVEL;
            }
        }

        return result / 10;
    }
}
