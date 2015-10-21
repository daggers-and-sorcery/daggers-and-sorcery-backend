package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.modifier.CombatAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentAttributeBonusCalculator {

    private static final int EMPTY_EQUIPMENT_SLOT = 0;

    private final EquipmentManager equipmentManager;
    private final ItemDefinitionManager itemDefinitionManager;

    @Autowired
    public EquipmentAttributeBonusCalculator(EquipmentManager equipmentManager, ItemDefinitionManager itemDefinitionManager) {
        this.equipmentManager = equipmentManager;
        this.itemDefinitionManager = itemDefinitionManager;
    }

    public AttributeCalculationResult calculateEquipmentBonus(UserEntity userEntity, Attribute attribute) {
        AttributeCalculationResult result = new AttributeCalculationResult();

        EquipmentEntity equipmentEntity = equipmentManager.getEquipment(userEntity);

        for(EquipmentSlot slot : EquipmentSlot.values()) {
            int item = equipmentEntity.getEquipmentIdOnSlot(slot);

            if(item != EMPTY_EQUIPMENT_SLOT) {
                calculateItemModifiers(result, attribute, itemDefinitionManager.getItemDefinition(item));
            }
        }

        return result;
    }

    private void calculateItemModifiers(AttributeCalculationResult result, Attribute attribute, ItemDefinition itemDefinition) {
        itemDefinition.getAllModifiers().stream().filter(modifierDefinition -> modifierDefinition.getAttribute() == attribute).forEach(modifierDefinition -> {
            result.increaseValue(modifierDefinition.getAmount());

            if (modifierDefinition instanceof CombatAttributeModifierDefinition) {
                calculateCombatModifier(result, (CombatAttributeModifierDefinition) modifierDefinition);
            }
        });
    }

    private void calculateCombatModifier(AttributeCalculationResult result, CombatAttributeModifierDefinition combatAttributeModifierDefinition) {
        result.increaseD2(combatAttributeModifierDefinition.getD2());
        result.increaseD4(combatAttributeModifierDefinition.getD4());
        result.increaseD6(combatAttributeModifierDefinition.getD6());
        result.increaseD8(combatAttributeModifierDefinition.getD8());
        result.increaseD10(combatAttributeModifierDefinition.getD10());
    }
}
