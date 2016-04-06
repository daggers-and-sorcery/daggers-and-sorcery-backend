package com.morethanheroic.swords.explore.domain.event.entry.impl;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatManager;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.domain.event.entry.ExplorationEventEntry;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.CombatExplorationEventEntryResult;
import lombok.Builder;

@Builder
public class CombatExplorationEventEntry implements ExplorationEventEntry {

    private final int monster;
    private final CombatManager combatManager;

    @Override
    public ExplorationEventEntryResult handleExploration(ExplorationContext explorationContext) {
        CombatResult combatResult = combatManager.initiateCombat(explorationContext.getUserEntity(), monster);

        return CombatExplorationEventEntryResult.builder()
                .combatMessages(combatResult.getCombatMessages())
                .build();
    }
}
