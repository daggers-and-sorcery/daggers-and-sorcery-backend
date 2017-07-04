package com.morethanheroic.swords.combat.bonus.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * A generic class that could provide a bonus to everything.
 */
@Getter
@Builder
public class CombatBonus {

    private final int value;
    private final int d2;
    private final int d4;
    private final int d6;
    private final int d8;
    private final int d10;

    /**
     * Create a new combat bonus instance that contains the sum of the bonuses by the instance we are adding to and the added instance.
     *
     * @param combatBonus the combat bonus we are wish to add to this one
     * @return the new combat bonus
     */
    public CombatBonus add(final CombatBonus combatBonus) {
        return CombatBonus.builder()
                .value(value + combatBonus.value)
                .d2(d2 + combatBonus.d2)
                .d4(d4 + combatBonus.d4)
                .d6(d6 + combatBonus.d6)
                .d8(d8 + combatBonus.d8)
                .d10(d10 + combatBonus.d10)
                .build();
    }
}
