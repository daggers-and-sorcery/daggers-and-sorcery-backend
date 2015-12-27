package com.morethanheroic.swords.equipment.service;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.modifier.calculator.AttributeModifierCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentAttributeModifierCalculator implements AttributeModifierCalculator {

    @Autowired
    private EquipmentAttributeBonusProvider equipmentAttributeBonusProvider;

    @Override
    public List<AttributeModifierEntry> calculate(UserEntity user, Attribute attribute) {
        return Lists.newArrayList(
                new AttributeModifierEntry(AttributeModifierType.EQUIPMENT, AttributeModifierValueType.VALUE, new AttributeModifierValue(equipmentAttributeBonusProvider.calculateBonus(user, attribute)))
        );
    }
}
