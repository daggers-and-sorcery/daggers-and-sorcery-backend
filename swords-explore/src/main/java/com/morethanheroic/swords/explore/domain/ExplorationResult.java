package com.morethanheroic.swords.explore.domain;


import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ExplorationResult {

    private final List<ExplorationEventEntryResult> explorationEventEntryResults;
}
