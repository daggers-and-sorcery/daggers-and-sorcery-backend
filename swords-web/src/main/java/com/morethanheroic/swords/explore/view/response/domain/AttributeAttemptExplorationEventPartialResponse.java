package com.morethanheroic.swords.explore.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@Builder
@Getter
public class AttributeAttemptExplorationEventPartialResponse extends PartialResponse {

    private final ExplorationEventResponseType eventType = ExplorationEventResponseType.ATTRIBUTE_ATTEMPT;

    private final Collection<? extends PartialResponse> messages;
}
