package com.morethanheroic.swords.combat.service.equipment;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.ItemModifierToAttributeConverter;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.AttributeCalculationResultFactory;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.SimpleValueAttributeCalculationResult;
import com.morethanheroic.swords.equipment.EquipmentEntityFactory;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.domain.EquipmentSlotEntity;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * Calculate and provide the bonuses of the equipments for the attributes.
 */
@Service
@RequiredArgsConstructor
public class EquipmentAttributeBonusProvider implements AttributeBonusProvider {

    private final EquipmentEntityFactory equipmentEntityFactory;
    private final ItemModifierToAttributeConverter itemModifierToAttributeConverter;
    private final AttributeCalculationResultFactory attributeCalculationResultFactory;

    @Override
    public Optional<SimpleValueAttributeCalculationResult> calculateBonus(final UserEntity userEntity, final Attribute attribute) {
        final SimpleValueAttributeCalculationResult result = attributeCalculationResultFactory.newResult(attribute);
        final EquipmentEntity equipmentEntity = equipmentEntityFactory.getEntity(userEntity);

        Arrays.stream(EquipmentSlot.values())
                .map(equipmentEntity::getEquipmentSlot)
                .filter(EquipmentSlotEntity::hasItem)
                .forEach(slot -> calculateModifiersOnSlot(result, slot, equipmentEntity));

        return Optional.of(result);
    }

    private void calculateModifiersOnSlot(final SimpleValueAttributeCalculationResult result, final EquipmentSlotEntity equipmentSlotEntity, final EquipmentEntity equipmentEntity) {
        if (equipmentSlotEntity.isSlot(EquipmentSlot.QUIVER)) {
            final EquipmentSlotEntity weaponEquipmentSlotEntity = equipmentEntity.getEquipmentSlot(EquipmentSlot.WEAPON);

            if (weaponEquipmentSlotEntity.hasItem() && weaponEquipmentSlotEntity.getItem().get().hasType(ItemType.CROSSBOWS, ItemType.LONGBOWS, ItemType.SHORTBOWS)) {
                calculateItemModifiers(result, equipmentSlotEntity.getItem().get());
            }
        } else {
            calculateItemModifiers(result, equipmentSlotEntity.getItem().get());
        }
    }

    private void calculateItemModifiers(SimpleValueAttributeCalculationResult result, ItemDefinition itemDefinition) {
        itemDefinition.getModifiers().stream()
                .filter(modifierDefinition -> itemModifierToAttributeConverter.convert(modifierDefinition.getModifier()) == result.getAttribute())
                .forEach(modifierDefinition -> {
                    result.increaseValue(modifierDefinition.getAmount());

                    if (result.getAttribute() instanceof CombatAttribute) {
                        ((DiceValueAttributeCalculationResult) result).increaseD2(modifierDefinition.getD2());
                        ((DiceValueAttributeCalculationResult) result).increaseD4(modifierDefinition.getD4());
                        ((DiceValueAttributeCalculationResult) result).increaseD6(modifierDefinition.getD6());
                        ((DiceValueAttributeCalculationResult) result).increaseD8(modifierDefinition.getD8());
                        ((DiceValueAttributeCalculationResult) result).increaseD10(modifierDefinition.getD10());
                    }
                });
    }
}
