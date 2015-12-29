package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierUnitType;
import com.morethanheroic.swords.attribute.service.calc.GeneralAttributeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralAttributeModifierCalculator implements AttributeModifierCalculator<GeneralAttribute> {

    @Autowired
    private GeneralAttributeCalculator generalAttributeCalculator;

    @Override
    public List<AttributeModifierEntry> calculate(UserEntity user, GeneralAttribute attribute) {
        return Lists.newArrayList(
                new AttributeModifierEntry(AttributeModifierType.SKILL, AttributeModifierUnitType.VALUE, new AttributeModifierValue(generalAttributeCalculator.calculatePointsBonusBySkills(user, attribute))),
                new AttributeModifierEntry(AttributeModifierType.INITIAL, AttributeModifierUnitType.VALUE, new AttributeModifierValue(attribute.getInitialValue()))
        );
    }
}
