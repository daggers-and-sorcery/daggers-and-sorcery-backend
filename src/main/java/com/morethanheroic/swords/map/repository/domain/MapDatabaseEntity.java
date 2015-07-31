package com.morethanheroic.swords.map.repository.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "map_entity")
public class MapDatabaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="map_id", insertable=false, updatable=false)
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
