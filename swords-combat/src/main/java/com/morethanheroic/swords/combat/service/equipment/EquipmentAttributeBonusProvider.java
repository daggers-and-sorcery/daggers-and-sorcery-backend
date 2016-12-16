package com.morethanheroic.swords.combat.service.equipment;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.ItemModifierToAttributeConverter;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.equipment.EquipmentEntityFactory;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentFacade;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Calculate and provide the bonuses of the equipments for the attributes.
 */
@Service
@RequiredArgsConstructor
public class EquipmentAttributeBonusProvider implements AttributeBonusProvider {

    private static final int EMPTY_EQUIPMENT_SLOT = 0;

    private final EquipmentEntityFactory equipmentEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final ItemModifierToAttributeConverter itemModifierToAttributeConverter;
    private final CombatUtil combatUtil;

    @Override
    public Optional<AttributeCalculationResult> calculateBonus(UserEntity userEntity, Attribute attribute) {
        final AttributeCalculationResult result = createAttributeCalculationResult(attribute);
        final EquipmentEntity equipmentEntity = equipmentEntityFactory.getEntity(userEntity);

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            final int item = equipmentEntity.getEquipmentIdOnSlot(slot);

            if (item != EMPTY_EQUIPMENT_SLOT) {
                calculateItemModifiers(result, itemDefinitionCache.getDefinition(item));
            }
        }

        calculateNoWeaponFistfightModifiers(result, userEntity);

        return Optional.of(result);
    }

    private AttributeCalculationResult createAttributeCalculationResult(Attribute attribute) {
        if (attribute instanceof CombatAttribute) {
            return new CombatAttributeCalculationResult((CombatAttribute) attribute);
        } else {
            return new AttributeCalculationResult(attribute);
        }
    }

    private void calculateNoWeaponFistfightModifiers(AttributeCalculationResult result, UserEntity userEntity) {
        if (combatUtil.isFistfighting(userEntity)) {
            if (result.getAttribute() == CombatAttribute.ATTACK) {
                ((CombatAttributeCalculationResult) result).increaseD4(1 + (int) Math.floor(globalAttributeCalculator.calculateActualValue(userEntity, SkillAttribute.FISTFIGHT).getValue() / 4));
            } else if (result.getAttribute() == CombatAttribute.DAMAGE) {
                ((CombatAttributeCalculationResult) result).increaseD2(1 + (int) Math.floor(globalAttributeCalculator.calculateActualValue(userEntity, SkillAttribute.FISTFIGHT).getValue() / 4));
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
