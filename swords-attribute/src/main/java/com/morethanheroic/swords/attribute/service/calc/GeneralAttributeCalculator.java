package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.calc.domain.GeneralAttributeData;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralAttributeCalculator implements AttributeCalculator<GeneralAttribute> {

    private static final int STARTING_SKILL_LEVEL = 1;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Autowired
    private SkillFacade skillFacade;

    @Autowired
    private SkillTypeCalculator skillTypeCalculator;

    @Override
    public GeneralAttributeData calculateAttributeValue(UserEntity user, GeneralAttribute attribute) {
        return GeneralAttributeData.generalAttributeDataBuilder()
                .attribute(attribute)
                .actual(globalAttributeCalculator.calculateActualValue(user, attribute))
                .maximum(globalAttributeCalculator.calculateMaximumValue(user, attribute))
                .modifierData(globalAttributeModifierCalculator.calculateModifierData(user, attribute))
                .pointsToNextLevel(calculatePointsToAttributeLevel(user, attribute))
                .build();
    }

    public int calculatePointsToAttributeLevel(UserEntity user, Attribute attribute) {
        int result = 0;

        for (SkillAttribute skill : SkillAttribute.values()) {
            if (skill.getIncrementedAttribute() == attribute) {
                result += skillFacade.getSkills(user).getSkillLevel(skillTypeCalculator.getSkillTypeFromSkillAttribute(skill)) - STARTING_SKILL_LEVEL;
            }
        }

        return result % 10;
    }

    public int calculatePointsBonusBySkills(UserEntity user, Attribute attribute) {
        int result = 0;

        for (SkillAttribute skill : SkillAttribute.values()) {
            if (skill.getIncrementedAttribute() == attribute) {
                result += skillFacade.getSkills(user).getSkillLevel(skillTypeCalculator.getSkillTypeFromSkillAttribute(skill)) - STARTING_SKILL_LEVEL;
            }
        }

        return result / 10;
    }
}
