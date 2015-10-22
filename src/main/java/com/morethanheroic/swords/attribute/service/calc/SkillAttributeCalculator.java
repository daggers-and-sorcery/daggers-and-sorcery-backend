package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.SkillAttributeData;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillAttributeCalculator implements AttributeCalculator {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Autowired
    private SkillManager skillManager;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, Attribute attribute) {
        if (!(attribute instanceof SkillAttribute)) {
            throw new IllegalArgumentException("The attribute must be an instance of SkillAttribute.");
        }

        SkillAttribute localAttribute = (SkillAttribute) attribute;
        
        SkillAttributeData.SkillAttributeDataBuilder attributeDataBuilder = new SkillAttributeData.SkillAttributeDataBuilder(localAttribute);

        attributeDataBuilder.setActual(globalAttributeCalculator.calculateActualValue(user, localAttribute));
        attributeDataBuilder.setMaximum(globalAttributeCalculator.calculateMaximumValue(user, localAttribute));
        attributeDataBuilder.setAttributeModifierDataArray(globalAttributeModifierCalculator.calculateModifierData(user, localAttribute));
        attributeDataBuilder.setActualXp(skillManager.getSkills(user).getSkillXp(localAttribute));
        attributeDataBuilder.setNextLevelXp(skillManager.getSkills(user).getSkillXpToNextLevel(localAttribute));
        attributeDataBuilder.setXpBetweenLevels(skillManager.getSkills(user).getSkillXpBetweenNextLevel(localAttribute));

        return attributeDataBuilder.build();
    }
}
