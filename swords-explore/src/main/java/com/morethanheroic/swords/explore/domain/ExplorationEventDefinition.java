package com.morethanheroic.swords.explore.domain;

import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventRarity;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventTerrain;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExplorationEventDefinition {

    private final int id;
    private final String name;
    private final ExplorationZone location;
    private final ExplorationEventTerrain terrain;
    private final ExplorationEventRarity rarity;
}