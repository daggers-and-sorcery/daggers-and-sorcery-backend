package com.morethanheroic.swords.map.domain;

import com.morethanheroic.swords.map.service.domain.TileDefinition;

public class TileEntity {

    private final TileDefinition tileDefinition;

    public TileEntity(TileDefinition tileDefinition) {
        this.tileDefinition = tileDefinition;
    }

    public boolean isWalkable() {
        return tileDefinition.isWalkable();
    }
}
