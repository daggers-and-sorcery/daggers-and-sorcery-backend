package com.morethanheroic.swords.combat.service.event.damage;

import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerResult;

import java.util.Optional;

/**
 * This event will contain parts of the damage calculation.
 */
public interface DamageCombatEventHandler {

    Optional<DamageCombatEventHandlerResult> handleEvent(final DamageCombatEventHandlerContext damageCombatEventHandlerContext);
}
