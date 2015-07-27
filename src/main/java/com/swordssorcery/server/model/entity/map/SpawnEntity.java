package com.swordssorcery.server.model.entity.map;

import com.swordssorcery.server.model.definition.map.MapSpawnEntryDefinition;

public class SpawnEntity {

    private final MapSpawnEntryDefinition mapSpawnEntryDefinition;

    public SpawnEntity(MapSpawnEntryDefinition mapSpawnEntryDefinition) {
        this.mapSpawnEntryDefinition = mapSpawnEntryDefinition;
    }

    public int getMonsterId() {
        return this.mapSpawnEntryDefinition.getMonsterId();
    }

    /**
     * @return The chance that this monster will spawn on the map compared to the other monsters chances.
     */
    public int getChance() {
        return this.mapSpawnEntryDefinition.getChance();
    }
}
