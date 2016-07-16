package com.morethanheroic.swords.explore.service.event.evaluator.domain;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CombatEventEntryEvaluatorResult {

    private CombatResult combatResult;
    private ExplorationEventEntryResult result;
}
