package com.morethanheroic.swords.map.repository.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "map_entity")
public class MapDatabaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "map", fetch = FetchType.EAGER)
    private List<MapObectDatabaseEntity> spawns = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MapObectDatabaseEntity> getSpawns() {
        return spawns;
    }

    public void addSpawn(MapObectDatabaseEntity spawn) {
        spawns.add(spawn);
    }

    public void setSpawns(ArrayList<MapObectDatabaseEntity> spawns) {
        this.spawns = spawns;
    }
}
