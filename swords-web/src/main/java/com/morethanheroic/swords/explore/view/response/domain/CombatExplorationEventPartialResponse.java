package com.morethanheroic.swords.explore.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@Builder
@Getter
public class CombatExplorationEventPartialResponse extends PartialResponse {

    private final ExplorationEventResponseType eventType = ExplorationEventResponseType.COMBAT;

    private final Collection<? extends PartialResponse> combatMessages;

    private final PartialResponse status;
}
