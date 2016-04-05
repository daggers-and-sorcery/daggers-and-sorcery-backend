package com.morethanheroic.swords.explore.domain.event.entry.impl;

import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.domain.event.entry.ExplorationEventEntry;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import lombok.Builder;

@Builder
public class TextExplorationEventEntry implements ExplorationEventEntry {

    private final String content;

    @Override
    public ExplorationEventEntryResult handleExploration(ExplorationContext explorationContext) {
        return TextExplorationEventEntryResult.builder()
                .content(content)
                .build();
    }
}
