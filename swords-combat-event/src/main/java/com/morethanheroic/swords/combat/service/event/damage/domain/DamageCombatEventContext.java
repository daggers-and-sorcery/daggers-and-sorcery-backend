package com.morethanheroic.swords.combat.service.event.damage.domain;

import com.morethanheroic.swords.combat.service.event.damage.DamageCombatEventHandler;
import lombok.Builder;
import lombok.Getter;

/**
 * Used in the {@link DamageCombatEventHandler}.
 */
@Getter
@Builder
public class DamageCombatEventContext {

    private final DamageType damageType;
}
