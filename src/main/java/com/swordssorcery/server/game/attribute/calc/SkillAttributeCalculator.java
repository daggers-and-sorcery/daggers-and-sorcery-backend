package com.swordssorcery.server.game.attribute.calc;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.AttributeCalculator;
import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.game.attribute.data.SkillAttributeData;
import com.swordssorcery.server.game.attribute.modifier.AttributeModifierCalculator;
import com.swordssorcery.server.game.attribute.type.SkillAttribute;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillAttributeCalculator implements AttributeCalculator {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private AttributeModifierCalculator attributeModifierCalculator;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, Attribute attribute) {
        if (!(attribute instanceof SkillAttribute)) {
            throw new IllegalArgumentException("The attribute must be an instance of SkillAttribute.");
        }

        SkillAttribute localAttribute = (SkillAttribute) attribute;
        
        SkillAttributeData.SkillAttributeDataBuilder attributeDataBuilder = new SkillAttributeData.SkillAttributeDataBuilder(localAttribute);

        attributeDataBuilder.setActual(globalAttributeCalculator.calculateActualValue(user, localAttribute));
        attributeDataBuilder.setMaximum(globalAttributeCalculator.calculateMaximumValue(user, localAttribute));
        attributeDataBuilder.setAttributeModifierDataArray(attributeModifierCalculator.calculateModifierData(user, localAttribute));
        attributeDataBuilder.setActualXp(user.getSkills().getSkillXp(localAttribute));
        attributeDataBuilder.setNextLevelXp(user.getSkills().getSkillXpToNextLevel(localAttribute));
        attributeDataBuilder.setXpBetweenLevels(user.getSkills().getSkillXpBetweenNextLevel(localAttribute));

        return attributeDataBuilder.build();
    }
}
