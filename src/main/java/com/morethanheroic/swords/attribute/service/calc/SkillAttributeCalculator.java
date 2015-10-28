package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.SkillAttributeData;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillAttributeCalculator implements AttributeCalculator<SkillAttribute> {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Autowired
    private SkillManager skillManager;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, SkillAttribute attribute) {
        SkillAttributeData.SkillAttributeDataBuilder attributeDataBuilder = new SkillAttributeData.SkillAttributeDataBuilder(attribute);

        attributeDataBuilder.setActual(globalAttributeCalculator.calculateActualValue(user, attribute));
        attributeDataBuilder.setMaximum(globalAttributeCalculator.calculateMaximumValue(user, attribute));
        attributeDataBuilder.setAttributeModifierData(globalAttributeModifierCalculator.calculateModifierData(user, attribute));
        attributeDataBuilder.setActualXp(skillManager.getSkills(user).getSkillXp(attribute));
        attributeDataBuilder.setNextLevelXp(skillManager.getSkills(user).getSkillXpToNextLevel(attribute));
        attributeDataBuilder.setXpBetweenLevels(skillManager.getSkills(user).getSkillXpBetweenNextLevel(attribute));

        return attributeDataBuilder.build();
    }
}
