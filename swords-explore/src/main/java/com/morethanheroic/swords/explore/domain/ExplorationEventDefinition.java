package com.morethanheroic.swords.explore.domain;

import com.morethanheroic.swords.explore.domain.event.ExplorationEventLocation;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventRarity;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventTerrain;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExplorationEventDefinition {

    private int id;
    private ExplorationEventLocation location;
    private ExplorationEventTerrain terrain;
    private ExplorationEventRarity rarity;
}