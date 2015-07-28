package com.morethanheroic.swords.map.service.domain;

public class TileDefinition {

    private final boolean walkable;

    public TileDefinition(boolean walkable) {
        this.walkable = walkable;
    }

    public boolean isWalkable() {
        return walkable;
    }
}
