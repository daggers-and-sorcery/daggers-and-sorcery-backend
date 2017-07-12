package com.morethanheroic.swords.explore.domain.event.result.impl;

import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CombatExplorationEventEntryResult implements ExplorationEventEntryResult {

    private final List<CombatStep> combatSteps;
    private final boolean combatEnded;
    private final boolean playerDead;
}
