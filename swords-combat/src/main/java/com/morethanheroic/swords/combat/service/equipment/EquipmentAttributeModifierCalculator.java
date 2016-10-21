package com.morethanheroic.swords.combat.service.equipment;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierUnitType;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.modifier.calculator.AttributeModifierCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.attribute.service.modifier.domain.CombatAttributeModifierValue;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EquipmentAttributeModifierCalculator implements AttributeModifierCalculator {

    @Autowired
    private EquipmentAttributeBonusProvider equipmentAttributeBonusProvider;

    //Todo: write a factory for AttributeModifierEntry and use that to initialize the instances.
    @Override
    public List<AttributeModifierEntry> calculate(UserEntity user, Attribute attribute) {
        final AttributeCalculationResult attributeCalculationResult = equipmentAttributeBonusProvider.calculateBonus(user, attribute);

        if (!attributeCalculationResult.isZero()) {
            if (attribute instanceof CombatAttribute) {
                return Lists.newArrayList(
                        new AttributeModifierEntry(AttributeModifierType.EQUIPMENT, AttributeModifierUnitType.VALUE, new CombatAttributeModifierValue((CombatAttributeCalculationResult) attributeCalculationResult))
                );
            } else {
                return Lists.newArrayList(
                        new AttributeModifierEntry(AttributeModifierType.EQUIPMENT, AttributeModifierUnitType.VALUE, new AttributeModifierValue(attributeCalculationResult))
                );
            }
        }

        return Collections.emptyList();
    }
}
