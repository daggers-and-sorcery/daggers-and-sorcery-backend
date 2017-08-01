package com.morethanheroic.swords.explore.domain;

import com.morethanheroic.swords.explore.domain.event.ExplorationEventLocation;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventRarity;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventTerrain;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExplorationEventDefinition {

    private final int id;
    private final String name;
    private final ExplorationEventLocation location;
    private final ExplorationEventTerrain terrain;
    private final ExplorationEventRarity rarity;
}