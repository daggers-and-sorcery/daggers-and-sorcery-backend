package com.swordssorcery.server.game.map.loader;

public class RawMap {

    private int height;
    private int width;
    private int tileheight;
    private int tilewidth;
    private RawMapLayer[] layers;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getTileheight() {
        return tileheight;
    }

    public void setTileheight(int tileheight) {
        this.tileheight = tileheight;
    }

    public int getTilewidth() {
        return tilewidth;
    }

    public void setTilewidth(int tilewidth) {
        this.tilewidth = tilewidth;
    }

    public RawMapLayer[] getLayers() {
        return layers;
    }

    public void setLayers(RawMapLayer[] layers) {
        this.layers = layers;
    }
}
