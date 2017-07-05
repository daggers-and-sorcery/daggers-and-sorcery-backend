package com.morethanheroic.swords.combat.service.event;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;

import java.util.List;

/**
 * This event is fired when a monster dies in combat.
 */
public interface MonsterDeathCombatEventHandler {

    List<CombatStep> handleEvent(final CombatEntity deathEntity, final CombatEntity killerEntity);
}
