package com.swordssorcery.server.model.entity.map;

import com.swordssorcery.server.model.definition.map.TileDefinition;

public class TileEntity {

    private final TileDefinition tileDefinition;

    public TileEntity(TileDefinition tileDefinition) {
        this.tileDefinition = tileDefinition;
    }

    public boolean isWalkable() {
        return tileDefinition.isWalkable();
    }
}
