package com.morethanheroic.swords.map.repository.domain;

import javax.persistence.*;

@Entity(name = "map_object")
public class MapObectDatabaseEntity {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "map_id")
    private MapDatabaseEntity map;

    @Column
    private int x;

    @Column
    private int y;

    @Column
    private MapObjectType type;

    public MapObectDatabaseEntity() {
    }

    public MapObectDatabaseEntity(MapDatabaseEntity map, int x, int y, MapObjectType type) {
        this.map = map;
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
}
