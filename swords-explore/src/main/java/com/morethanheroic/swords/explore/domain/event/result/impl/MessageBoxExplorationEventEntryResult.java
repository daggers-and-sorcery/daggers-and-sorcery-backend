package com.morethanheroic.swords.explore.domain.event.result.impl;

import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageBoxExplorationEventEntryResult implements ExplorationEventEntryResult {

    private final String content;
}
