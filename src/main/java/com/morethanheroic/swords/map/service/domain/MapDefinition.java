package com.morethanheroic.swords.map.service.domain;

public class MapDefinition {

    private final int id;
    private final int height;
    private final int width;
    private final TileDefinition[][] tileDefinitions;

    public MapDefinition(int id, int width, int height, TileDefinition[][] tileDefinitions) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.tileDefinitions = tileDefinitions;
    }

    public TileDefinition getTileDefinitionAt(int x, int y) {
        return tileDefinitions[x][y];
    }

    public String toString() {
        return "MapDefinition -> [width: " + getWidth() + ", height: " + getHeight() + "]";
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
