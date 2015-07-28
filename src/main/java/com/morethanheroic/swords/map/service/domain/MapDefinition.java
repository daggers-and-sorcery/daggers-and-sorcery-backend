package com.morethanheroic.swords.map.service.domain;

public class MapDefinition {

    private final int height;
    private final int width;
    private final TileDefinition[][] tileDefinitions;

    public MapDefinition(int width, int height, TileDefinition[][] tileDefinitions) {
        this.width = width;
        this.height = height;
        this.tileDefinitions = tileDefinitions;
    }

    public TileDefinition getTileDefinitionAt(int x, int y) {
        return tileDefinitions[x][y];
    }

    public String toString() {
        return "MapDefinition -> [width: " + width + ", height: " + height + "]";
    }
}
