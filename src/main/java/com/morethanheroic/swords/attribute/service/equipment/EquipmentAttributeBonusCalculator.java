package com.morethanheroic.swords.attribute.service.equipment;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.modifier.CombatAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentAttributeBonusCalculator {

    private static final int EMPTY_EQUIPMENT_SLOT = 0;

    @Autowired
    private EquipmentManager equipmentManager;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    public AttributeCalculationResult calculateEquipmentBonus(UserEntity userEntity, Attribute attribute) {
        AttributeCalculationResult result = new AttributeCalculationResult(attribute);

        EquipmentEntity equipmentEntity = equipmentManager.getEquipment(userEntity);

        for(EquipmentSlot slot : EquipmentSlot.values()) {
            int item = equipmentEntity.getEquipmentIdOnSlot(slot);

            if(item != EMPTY_EQUIPMENT_SLOT) {
                calculateItemModifiers(result, itemDefinitionCache.getItemDefinition(item));
            }
        }

        calculateNoWeaponFistfightModifiers(result, equipmentEntity, userEntity);

        return result;
    }

    private void calculateNoWeaponFistfightModifiers(AttributeCalculationResult result, EquipmentEntity equipmentEntity, UserEntity userEntity) {
        if (equipmentEntity.getEquipmentIdOnSlot(EquipmentSlot.WEAPON) == EMPTY_EQUIPMENT_SLOT) {
            if(result.getAttribute() == CombatAttribute.ATTACK) {
                result.increaseD4(1 + (int) Math.floor(globalAttributeCalculator.calculateActualValue(userEntity, SkillAttribute.FISTFIGHT).getValue() / 3));
            } else if (result.getAttribute() == CombatAttribute.DAMAGE) {
                result.increaseD2(1 + (int) Math.floor(globalAttributeCalculator.calculateActualValue(userEntity, SkillAttribute.FISTFIGHT).getValue() / 3));
            }
        }
    }

    private void calculateItemModifiers(AttributeCalculationResult result, ItemDefinition itemDefinition) {
        itemDefinition.getAllModifiers().stream().filter(modifierDefinition -> modifierDefinition.getAttribute() == result.getAttribute()).forEach(modifierDefinition -> {
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
