package com.morethanheroic.swords.map.service.domain;

public class TileDefinition {

    private final boolean walkable;
    private final int x;
    private final int y;
    private final int background;

    public TileDefinition(boolean walkable, int x, int y, int background) {
        this.walkable = walkable;
        this.x = x;
        this.y = y;
        this.background = background;
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

    public int getBackground() {
        return background;
    }
}
