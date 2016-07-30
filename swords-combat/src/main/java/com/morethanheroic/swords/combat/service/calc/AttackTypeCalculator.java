package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import org.springframework.stereotype.Service;

@Service
public class AttackTypeCalculator {

    public AttackType calculateAttackType(ItemDefinition item) {
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
                return AttackType.RANGED;
            case WAND:
            case STAFF:
            case SPECTRE:
                return AttackType.MAGIC;
            default:
                throw new IllegalArgumentException("No attack type found for type: " + item.getSubtype() + " on item: " + item.getId());
        }
    }
}
