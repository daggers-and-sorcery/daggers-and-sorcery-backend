package com.morethanheroic.swords.map.repository.domain;

public class MapObjectDatabaseEntity {

    private int id;

    private int object;

    private int mapId;

    private int x;

    private int y;

    private MapObjectType objectType;

    public MapObjectDatabaseEntity() {
    }

    public MapObjectDatabaseEntity(int object, int map, int x, int y, MapObjectType type) {
        this.setObject(object);
        this.mapId = map;
        this.x = x;
        this.y = y;
        this.objectType = type;
    }

    public int getId() {
        return id;
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
        return objectType;
    }

    public void setType(MapObjectType type) {
        this.objectType = type;
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

    @Override
    public String toString() {
        return "MapObjectDatabaseEntity -> [id: "+id+", map_id: "+mapId+", x: "+x+", map_id: "+y+", map_id: "+y+", object_type: "+objectType+"]";
    }
}
