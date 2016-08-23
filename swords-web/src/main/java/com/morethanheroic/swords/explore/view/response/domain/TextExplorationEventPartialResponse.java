package com.morethanheroic.swords.explore.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TextExplorationEventPartialResponse extends PartialResponse {

    private final ExplorationEventResponseType eventType = ExplorationEventResponseType.TEXT;
    
    private final String content;
}
