package com.morethanheroic.swords.explore.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventRarity;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventTerrain;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventInfoPartialResponse extends PartialResponse {

    private ExplorationEventTerrain terrain;
    private ExplorationEventRarity rarity;
}
