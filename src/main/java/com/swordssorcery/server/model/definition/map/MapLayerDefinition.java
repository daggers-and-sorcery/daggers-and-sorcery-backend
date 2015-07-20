package com.swordssorcery.server.model.definition.map;

public class MapLayerDefinition {

    private final String name;
    private final int width;
    private final int height;
    private final int[][] data;

    public MapLayerDefinition(String name, int width, int height, int[][] data) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getData() {
        return data;
    }
}
