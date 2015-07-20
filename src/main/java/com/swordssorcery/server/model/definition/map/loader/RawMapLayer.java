package com.swordssorcery.server.model.definition.map.loader;

public class RawMapLayer {

    private int[] data;
    private String name;
    private int height;
    private int width;

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public int getDataAt(int x, int y) {
        return data[x + width * y];
    }
}
