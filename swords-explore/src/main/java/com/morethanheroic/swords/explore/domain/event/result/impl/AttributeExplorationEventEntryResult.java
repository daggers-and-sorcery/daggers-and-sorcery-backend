package com.morethanheroic.swords.explore.domain.event.result.impl;

import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AttributeExplorationEventEntryResult implements ExplorationEventEntryResult {

    private final List<TextExplorationEventEntryResult> result;
}
