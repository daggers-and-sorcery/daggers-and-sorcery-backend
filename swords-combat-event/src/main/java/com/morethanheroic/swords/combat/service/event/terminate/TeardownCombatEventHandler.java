package com.morethanheroic.swords.combat.service.event.terminate;

import java.util.List;

import com.morethanheroic.swords.combat.service.event.terminate.domain.TeardownCombatEventContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;

/**
 * These events run after the combat is fully finished and the winner is calculated.
 */
public interface TeardownCombatEventHandler {

    List<CombatStep> handleEvent(final TeardownCombatEventContext teardownCombatEventContext);
}
