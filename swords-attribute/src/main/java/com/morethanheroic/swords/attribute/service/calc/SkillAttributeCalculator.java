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
                .actual(globalAttributeCalculator.calculateActualValue(user, attribute))
                .maximum(globalAttributeCalculator.calculateMaximumValue(user, attribute))
                .attributeModifierData(globalAttributeModifierCalculator.calculateModifierData(user, attribute))
                .actualXp(skillEntity.getSkillXp(skillType))
                .nextLevelXp(skillEntity.getSkillXpToNextLevel(skillType))
                .xpBetweenLevels(skillEntity.getSkillXpBetweenNextLevel(skillType))
                .build();
    }

    @Override
    public Class<SkillAttribute> getSupportedType() {
        return SkillAttribute.class;
    }
}
