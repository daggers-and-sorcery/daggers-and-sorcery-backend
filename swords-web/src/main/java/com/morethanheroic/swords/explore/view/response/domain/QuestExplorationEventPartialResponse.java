package com.morethanheroic.swords.explore.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

/**
 * A response that contains the data of a quest entry.
 */
@Builder
@Getter
public class QuestExplorationEventPartialResponse extends PartialResponse {

    private final ExplorationEventResponseType eventType = ExplorationEventResponseType.QUEST;

    private final String name;
    private final String description;
    private final int acceptQuestStage;
    private final int declineQuestStage;
}
