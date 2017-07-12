package com.morethanheroic.swords.combat.service.event.turn;

import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.service.event.turn.domain.StartTurnCombatEventContext;

import java.util.List;

/**
 * This event is fired at the start of a turn.
 */
public interface StartTurnCombatEventHandler {

    List<CombatStep> handleEvent(final StartTurnCombatEventContext startTurnCombatEventContext);
}
