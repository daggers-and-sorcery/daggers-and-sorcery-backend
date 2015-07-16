package com.swordssorcery.server.game.map;

public class MapDefinition {

    private final int height;
    private final int width;

    //TODO: Doesn't even need layers! Just MapCell[][] and Cell.isWalkable() etc!!!
    private final MapLayerDefinition[] layers;

    public MapDefinition(int width, int height, MapLayerDefinition[] layers) {
        this.width = width;
        this.height = height;
        this.layers = layers;
    }

    public String toString() {
        return "MapDefinition -> [width: " + width + ", height: " + height + ", layers: " + layers.length + "]";
    }
}
