package com.morethanheroic.swords.combat.service.event.death;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.step.domain.CombatStep;

import java.util.List;

/**
 * This event is fired when the player dies.
 */
public interface PlayerDeathCombatEventHandler {

    List<CombatStep> handleEvent(final CombatEntity deathEntity, final CombatEntity killerEntity);
}
