package com.morethanheroic.swords.map.repository.domain;

public class MapObjectDatabaseEntity {

    private int id;

    private int object;

    private int mapId;

    private int x;

    private int y;

    private MapObjectType type;

    public MapObjectDatabaseEntity() {
    }

    public MapObjectDatabaseEntity(int object, int map, int x, int y, MapObjectType type) {
        this.setObject(object);
        this.mapId = map;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public MapObjectType getType() {
        return type;
    }

    public void setType(MapObjectType type) {
        this.type = type;
    }

    public int getMap() {
        return mapId;
    }

    public int getObject() {
        return object;
    }

    public void setObject(int object) {
        this.object = object;
    }
}
