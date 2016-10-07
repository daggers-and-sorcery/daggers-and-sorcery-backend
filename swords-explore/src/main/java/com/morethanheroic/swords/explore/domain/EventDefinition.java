package com.morethanheroic.swords.explore.domain;

import com.morethanheroic.swords.explore.service.event.ExplorationEventLocation;
import com.morethanheroic.swords.explore.service.event.ExplorationEventRarity;
import com.morethanheroic.swords.explore.service.event.ExplorationEventTerrain;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventDefinition {

    private int id;
    private ExplorationEventLocation location;
    private ExplorationEventTerrain terrain;
    private ExplorationEventRarity rarity;
}