package com.morethanheroic.swords.attribute.service.equipment;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.modifier.CombatAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.service.ItemModifierToAttributeConverter;
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

    @Autowired
    private ItemModifierToAttributeConverter itemModifierToAttributeConverter;

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
        itemDefinition.getModifiers().stream().filter(modifierDefinition -> itemModifierToAttributeConverter.convert(modifierDefinition.getModifier()) == result.getAttribute()).forEach(modifierDefinition -> {
            result.increaseValue(modifierDefinition.getAmount());

            if (result.getAttribute() instanceof CombatAttribute) {
                result.increaseD2(modifierDefinition.getD2());
                result.increaseD4(modifierDefinition.getD4());
                result.increaseD6(modifierDefinition.getD6());
                result.increaseD8(modifierDefinition.getD8());
                result.increaseD10(modifierDefinition.getD10());
            }
        });
    }
}
