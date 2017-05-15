package com.morethanheroic.swords.explore.domain.event.result.impl;

import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestExplorationEventEntryResult implements ExplorationEventEntryResult {

    private final String name;
    private final String description;
    private final int acceptQuestStage;
    private final int declineQuestStage;
}
