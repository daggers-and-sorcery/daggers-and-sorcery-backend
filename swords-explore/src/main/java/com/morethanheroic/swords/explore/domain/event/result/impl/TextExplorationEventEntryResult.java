package com.morethanheroic.swords.explore.domain.event.result.impl;

import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TextExplorationEventEntryResult implements ExplorationEventEntryResult {

    private final String content;
}
