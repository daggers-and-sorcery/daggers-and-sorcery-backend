package com.morethanheroic.swords.map.repository.domain;

import java.util.ArrayList;
import java.util.List;

public class MapDatabaseEntity {

    private int id;

    private List<MapObjectDatabaseEntity> spawns = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MapObjectDatabaseEntity> getSpawns() {
        return spawns;
    }

    public void addSpawn(MapObjectDatabaseEntity spawn) {
        spawns.add(spawn);
    }

    public void setSpawns(ArrayList<MapObjectDatabaseEntity> spawns) {
        this.spawns = spawns;
    }
}
