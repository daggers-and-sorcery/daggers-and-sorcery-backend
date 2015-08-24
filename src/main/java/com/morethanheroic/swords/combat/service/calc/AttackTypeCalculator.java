package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.item.service.domain.ItemDefinition;
import org.springframework.stereotype.Service;

@Service
public class AttackTypeCalculator {

    public AttackType calculateAttackType(ItemDefinition item) {
        switch (item.getType()) {
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
            default:
                throw new IllegalArgumentException("No attack type found for type: " + item.getType() + " on item: " + item.getId());
        }
    }
}
