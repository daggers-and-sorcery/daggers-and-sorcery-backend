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

    public int getX() {
        return tileDefinition.getX();
    }

    public int getY() {
        return tileDefinition.getY();
    }

    public int getBackground() {
        return tileDefinition.getBackground();
    }

    //TODO: objects ome here later! Like bush,trees, houses etc
    public Object[] getObjects() {
        return new Object[0];
    }
}
