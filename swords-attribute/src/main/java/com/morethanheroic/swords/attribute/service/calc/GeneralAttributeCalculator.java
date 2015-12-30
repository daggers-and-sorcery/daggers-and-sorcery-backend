package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.AttributeFacade;
import com.morethanheroic.swords.attribute.service.calc.domain.data.GeneralAttributeData;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Used to calculate a {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#GENERAL} attribute's all data related to the player.
 */
@Service
public class GeneralAttributeCalculator implements AttributeCalculator<GeneralAttribute> {

    private static final int STARTING_SKILL_LEVEL = 1;

    @Autowired
    private AttributeFacade attributeFacade;

    @Autowired
    private SkillFacade skillFacade;

    @Autowired
    private SkillTypeCalculator skillTypeCalculator;

    @Override
    public GeneralAttributeData calculateAttributeValue(UserEntity user, GeneralAttribute attribute) {
        return GeneralAttributeData.generalAttributeDataBuilder()
                .attribute(attribute)
                .actual(attributeFacade.calculateAttributeValue(user, attribute))
                .maximum(attributeFacade.calculateAttributeMaximumValue(user, attribute))
                .modifierData(attributeFacade.calculateAttributeModifierData(user, attribute))
                .pointsToNextLevel(calculatePointsToAttributeLevel(user, attribute))
                .build();
    }

    public int calculatePointsToAttributeLevel(UserEntity user, Attribute attribute) {
        final SkillEntity skillEntity = skillFacade.getSkills(user);

        //TODO: this can be further optimized.
        int result = 0;
        for (SkillAttribute skill : SkillAttribute.values()) {
            if (skill.getIncrementedAttribute() == attribute) {
                result += skillEntity.getSkillLevel(skillTypeCalculator.getSkillTypeFromSkillAttribute(skill)) - STARTING_SKILL_LEVEL;
            }
        }

        return result % 10;
    }

    public int calculatePointsBonusBySkills(UserEntity user, Attribute attribute) {
        final SkillEntity skillEntity = skillFacade.getSkills(user);

        //TODO: this can be further optimized.
        int result = 0;
        for (SkillAttribute skill : SkillAttribute.values()) {
            if (skill.getIncrementedAttribute() == attribute) {
                result += skillEntity.getSkillLevel(skillTypeCalculator.getSkillTypeFromSkillAttribute(skill)) - STARTING_SKILL_LEVEL;
            }
        }

        return result / 10;
    }
}
