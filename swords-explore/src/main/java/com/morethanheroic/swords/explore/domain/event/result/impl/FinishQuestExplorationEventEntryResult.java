package com.morethanheroic.swords.explore.domain.event.result.impl;

import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FinishQuestExplorationEventEntryResult implements ExplorationEventEntryResult {

    private final QuestDefinition quest;
}
