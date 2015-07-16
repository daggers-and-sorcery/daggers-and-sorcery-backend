package com.swordssorcery.server.game.map;

public class MapDefinition {

    private final int height;
    private final int width;
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
