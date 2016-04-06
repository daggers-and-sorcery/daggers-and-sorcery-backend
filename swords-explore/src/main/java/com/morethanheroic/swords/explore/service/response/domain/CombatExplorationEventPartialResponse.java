package com.morethanheroic.swords.explore.service.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CombatExplorationEventPartialResponse extends PartialResponse {

    private final ExplorationEventResponseType eventType = ExplorationEventResponseType.COMBAT;

    private final List<CombatMessage> combatMessages;
}
