package com.morethanheroic.swords.combat.service.event.damage;

import lombok.Builder;
import lombok.Getter;

/**
 * Used in the {@link DamageCombatEventHandler}.
 */
@Getter
@Builder
public class DamageEventCalculationContext {

    private final DamageType damageType;
}
