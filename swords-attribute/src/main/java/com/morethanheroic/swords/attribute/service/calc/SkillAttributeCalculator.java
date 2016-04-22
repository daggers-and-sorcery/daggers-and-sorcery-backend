package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.data.SkillAttributeData;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Used to calculate a {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#SKILL} attribute's all data related to the player.
 */
@Service
public class SkillAttributeCalculator implements AttributeCalculator<SkillAttribute> {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Autowired
    private SkillFacade skillFacade;

    @Autowired
    private SkillTypeCalculator skillTypeCalculator;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, SkillAttribute attribute) {
        final SkillType skillType = skillTypeCalculator.getSkillTypeFromSkillAttribute(attribute);
        final SkillEntity skillEntity = skillFacade.getSkills(user);

        return SkillAttributeData.skillAttributeDataBuilder()
                .attribute(attribute)
                .actual(globalAttributeCalculator.calculateActualValue(user, attribute))
                .maximum(globalAttributeCalculator.calculateMaximumValue(user, attribute))
                .attributeModifierData(globalAttributeModifierCalculator.calculateModifierData(user, attribute))
                .actualXp(skillEntity.getExperience(skillType))
                .nextLevelXp(skillEntity.getExperienceToNextLevel(skillType))
                .xpBetweenLevels(skillEntity.getExperienceBetweenNextLevel(skillType))
                .build();
    }

    @Override
    public Class<SkillAttribute> getSupportedType() {
        return SkillAttribute.class;
    }
}
