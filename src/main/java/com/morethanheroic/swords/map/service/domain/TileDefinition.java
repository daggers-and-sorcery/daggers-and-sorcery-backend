package com.morethanheroic.swords.map.service.domain;

public class TileDefinition {

    private final boolean walkable;
    private final int x;
    private final int y;

    public TileDefinition(boolean walkable, int x, int y) {
        this.walkable = walkable;
        this.x = x;
        this.y = y;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
