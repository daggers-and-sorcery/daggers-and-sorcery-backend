package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.GeneralAttributeData;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralAttributeCalculator implements AttributeCalculator {

    private static int STARTING_SKILL_LEVEL = 1;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Autowired
    private SkillManager skillManager;

    @Override
    public GeneralAttributeData calculateAttributeValue(UserEntity user, Attribute attribute) {
        if (!(attribute instanceof GeneralAttribute)) {
            throw new IllegalArgumentException("The attribute must be an instance of GeneralAttribute.");
        }

        GeneralAttribute localAttribute = (GeneralAttribute) attribute;

        GeneralAttributeData.GeneralAttributeDataBuilder attributeDataBuilder = new GeneralAttributeData.GeneralAttributeDataBuilder(localAttribute);

        attributeDataBuilder.setActual(globalAttributeCalculator.calculateActualValue(user, localAttribute));
        attributeDataBuilder.setMaximum(globalAttributeCalculator.calculateMaximumValue(user, localAttribute));
        attributeDataBuilder.setAttributeModifierDataArray(globalAttributeModifierCalculator.calculateModifierData(user, localAttribute));
        attributeDataBuilder.setPointsToNextLevel(calculatePointsToAttributeLevel(user, attribute));

        return attributeDataBuilder.build();
    }

    public int calculatePointsToAttributeLevel(UserEntity user, Attribute attribute) {
        int result = 0;

        for (SkillAttribute skill : SkillAttribute.values()) {
            if(skill.getIncrementedAttribute() == attribute) {
                result += skillManager.getSkills(user).getSkillLevel(skill) - STARTING_SKILL_LEVEL;
            }
        }

        return result % 10;
    }

    public int calculatePointsBonusBySkills(UserEntity user, Attribute attribute) {
        int result = 0;

        for (SkillAttribute skill : SkillAttribute.values()) {
            if(skill.getIncrementedAttribute() == attribute) {
                result += skillManager.getSkills(user).getSkillLevel(skill) - STARTING_SKILL_LEVEL;
            }
        }

        return result / 10;
    }
}
