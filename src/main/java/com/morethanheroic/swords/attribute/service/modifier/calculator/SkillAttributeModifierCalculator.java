package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillAttributeModifierCalculator implements AttributeModifierCalculator<SkillAttribute> {

    @Autowired
    private SkillManager skillManager;

    @Override
    public List<AttributeModifierEntry> calculate(UserEntity user, SkillAttribute attribute) {
        return Lists.newArrayList(
                new AttributeModifierEntry(AttributeModifierType.LEVEL, AttributeModifierValueType.VALUE, new AttributeModifierValue(skillManager.getSkills(user).getSkillLevel(attribute)))
        );
    }
}
