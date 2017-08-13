package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import org.springframework.stereotype.Service;

@Service
public class AttackTypeCalculator {

    public AttackType calculateAttackType(final EquipmentEntity equipmentEntity) {
        final ItemDefinition item = equipmentEntity.getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON);

        if (item == null) {
            return AttackType.MELEE;
        }

        switch (item.getSubtype()) {
            case TWO_HANDED_CRUSHING_WEAPONS:
            case ONE_HANDED_CRUSHING_WEAPONS:
            case TWO_HANDED_AXES:
            case ONE_HANDED_AXES:
            case SHORTSWORDS:
            case LONGSWORDS:
            case POLEARMS:
            case DAGGERS:
                return AttackType.MELEE;
            case THROWING_WEAPONS:
            case LONGBOWS:
            case SHORTBOWS:
            case CROSSBOWS:
                if (equipmentEntity.getAmountOnSlot(EquipmentSlot.QUIVER) == 0) {
                    return AttackType.MELEE;
                }

                return AttackType.RANGED;
            case WAND:
            case STAFF:
            case SCEPTRE:
                return AttackType.MAGIC;
            default:
                throw new IllegalArgumentException("No attack type found for type: " + item.getSubtype() + " on item: " + item.getId());
        }
    }
}
