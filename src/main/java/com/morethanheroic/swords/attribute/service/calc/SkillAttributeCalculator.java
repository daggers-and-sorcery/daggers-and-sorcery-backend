package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.model.AttributeData;
import com.morethanheroic.swords.attribute.model.SkillAttributeData;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillAttributeCalculator implements AttributeCalculator {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private AttributeModifierCalculator attributeModifierCalculator;

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
        attributeDataBuilder.setAttributeModifierDataArray(attributeModifierCalculator.calculateModifierData(user, localAttribute));
        attributeDataBuilder.setActualXp(skillManager.getSkills(user).getSkillXp(localAttribute));
        attributeDataBuilder.setNextLevelXp(skillManager.getSkills(user).getSkillXpToNextLevel(localAttribute));
        attributeDataBuilder.setXpBetweenLevels(skillManager.getSkills(user).getSkillXpBetweenNextLevel(localAttribute));

        return attributeDataBuilder.build();
    }
}
