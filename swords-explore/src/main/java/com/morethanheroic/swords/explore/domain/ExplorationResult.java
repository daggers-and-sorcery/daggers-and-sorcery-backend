package com.morethanheroic.swords.explore.domain;

import com.morethanheroic.swords.explore.domain.event.ExplorationEventRarity;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventTerrain;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ExplorationResult {

    private final String name;
    private final ExplorationEventRarity rarity;
    private final ExplorationEventTerrain terrain;

    private final List<ExplorationEventEntryResult> explorationEventEntryResults = new ArrayList<>();

    public ExplorationResult addEventEntryResult(ExplorationEventEntryResult explorationEventEntryResult) {
        explorationEventEntryResults.add(explorationEventEntryResult);

        return this;
    }

    public ExplorationResult addEventEntryResults(List<? extends ExplorationEventEntryResult> explorationEventEntryResult) {
        explorationEventEntryResults.addAll(explorationEventEntryResult);

        return this;
    }
}
