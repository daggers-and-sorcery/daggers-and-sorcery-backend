package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierUnitType;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillAttributeModifierCalculator implements AttributeModifierCalculator<SkillAttribute> {

    @Autowired
    private SkillFacade skillFacade;

    @Autowired
    private SkillTypeCalculator skillTypeCalculator;

    @Override
    public List<AttributeModifierEntry> calculate(UserEntity user, SkillAttribute attribute) {
        final int skillLevel = skillFacade.getSkills(user).getSkillLevel(skillTypeCalculator.getSkillTypeFromSkillAttribute(attribute));

        return Lists.newArrayList(
                new AttributeModifierEntry(AttributeModifierType.LEVEL, AttributeModifierUnitType.VALUE, new AttributeModifierValue(skillLevel))
        );
    }
}
