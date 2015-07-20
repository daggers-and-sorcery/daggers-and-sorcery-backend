package com.swordssorcery.server.model.definition.map;

public class TileDefinition {

    private final boolean walkable;

    public TileDefinition(boolean walkable) {
        this.walkable = walkable;
    }

    public boolean isWalkable() {
        return walkable;
    }
}
