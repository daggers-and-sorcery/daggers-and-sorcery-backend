package com.morethanheroic.swords.map.repository.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;

@Entity(name = "map_entity")
public class MapDatabaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @Transient
    private ArrayList<MapObectDatabaseEntity> spawns = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<MapObectDatabaseEntity> getSpawns() {
        return spawns;
    }

    public void setSpawns(ArrayList<MapObectDatabaseEntity> spawns) {
        this.spawns = spawns;
    }
}
