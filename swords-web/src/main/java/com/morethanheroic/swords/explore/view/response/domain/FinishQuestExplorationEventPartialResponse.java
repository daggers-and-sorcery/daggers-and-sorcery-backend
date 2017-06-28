package com.morethanheroic.swords.explore.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FinishQuestExplorationEventPartialResponse extends PartialResponse {

    private final ExplorationEventResponseType eventType = ExplorationEventResponseType.FINISH_QUEST;

    private final int questId;
    private final List<String> rewards;
}
