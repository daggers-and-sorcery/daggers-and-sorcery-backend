package com.morethanheroic.swords.explore.domain.event.entry.impl;

import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.domain.event.entry.ExplorationEventEntry;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;

public class CombatExplorationEventEntry implements ExplorationEventEntry {

    @Override
    public ExplorationEventEntryResult handleExploration(ExplorationContext explorationContext) {
        return null;
    }
}
