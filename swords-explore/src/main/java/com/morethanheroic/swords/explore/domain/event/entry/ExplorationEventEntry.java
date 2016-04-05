package com.morethanheroic.swords.explore.domain.event.entry;

import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;

public interface ExplorationEventEntry {

    ExplorationEventEntryResult handleExploration(ExplorationContext explorationContext);
}
