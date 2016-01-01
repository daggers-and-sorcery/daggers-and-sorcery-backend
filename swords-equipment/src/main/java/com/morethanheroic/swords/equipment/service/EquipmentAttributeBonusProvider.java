package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.ItemModifierToAttributeConverter;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentAttributeBonusProvider implements AttributeBonusProvider {

    private static final int EMPTY_EQUIPMENT_SLOT = 0;

    @Autowired
    private EquipmentManager equipmentManager;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private ItemModifierToAttributeConverter itemModifierToAttributeConverter;

    @Override
    public AttributeCalculationResult calculateBonus(UserEntity userEntity, Attribute attribute) {
        final AttributeCalculationResult result = createAttributeCalculationResult(attribute);
        final EquipmentEntity equipmentEntity = equipmentManager.getEquipment(userEntity);

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            final int item = equipmentEntity.getEquipmentIdOnSlot(slot);

            if (item != EMPTY_EQUIPMENT_SLOT) {
                calculateItemModifiers(result, itemDefinitionCache.getDefinition(item));
            }
        }

        calculateNoWeaponFistfightModifiers(result, equipmentEntity, userEntity);

        return result;
    }

    private AttributeCalculationResult createAttributeCalculationResult(Attribute attribute) {
        if (attribute instanceof CombatAttribute) {
            return new CombatAttributeCalculationResult((CombatAttribute) attribute);
        } else {
            return new AttributeCalculationResult(attribute);
        }
    }

    private void calculateNoWeaponFistfightModifiers(AttributeCalculationResult result, EquipmentEntity equipmentEntity, UserEntity userEntity) {
        if (equipmentEntity.getEquipmentIdOnSlot(EquipmentSlot.WEAPON) == EMPTY_EQUIPMENT_SLOT) {
            if (result.getAttribute() == CombatAttribute.ATTACK) {
                ((CombatAttributeCalculationResult) result).increaseD4(1 + (int) Math.floor(globalAttributeCalculator.calculateActualValue(userEntity, SkillAttribute.FISTFIGHT).getValue() / 3));
            } else if (result.getAttribute() == CombatAttribute.DAMAGE) {
                ((CombatAttributeCalculationResult) result).increaseD2(1 + (int) Math.floor(globalAttributeCalculator.calculateActualValue(userEntity, SkillAttribute.FISTFIGHT).getValue() / 3));
            }
        }
    }

    private void calculateItemModifiers(AttributeCalculationResult result, ItemDefinition itemDefinition) {
        itemDefinition.getModifiers().stream().filter(modifierDefinition -> itemModifierToAttributeConverter.convert(modifierDefinition.getModifier()) == result.getAttribute()).forEach(modifierDefinition -> {
            result.increaseValue(modifierDefinition.getAmount());

            if (result.getAttribute() instanceof CombatAttribute) {
                ((CombatAttributeCalculationResult) result).increaseD2(modifierDefinition.getD2());
                ((CombatAttributeCalculationResult) result).increaseD4(modifierDefinition.getD4());
                ((CombatAttributeCalculationResult) result).increaseD6(modifierDefinition.getD6());
                ((CombatAttributeCalculationResult) result).increaseD8(modifierDefinition.getD8());
                ((CombatAttributeCalculationResult) result).increaseD10(modifierDefinition.getD10());
            }
        });
    }
}
