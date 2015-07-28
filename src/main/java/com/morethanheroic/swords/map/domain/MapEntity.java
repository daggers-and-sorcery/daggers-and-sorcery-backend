package com.morethanheroic.swords.map.domain;

import com.morethanheroic.swords.map.repository.domain.MapDatabaseEntity;
import com.morethanheroic.swords.map.service.domain.MapDefinition;
import com.morethanheroic.swords.map.service.domain.MapInfoDefinition;
import com.morethanheroic.swords.map.service.domain.MapSpawnEntryDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapEntity {

    private final int id;
    private final MapDefinition mapDefinition;
    private final MapInfoDefinition mapInfoDefinition;
    private final MapDatabaseEntity mapDatabaseEntity;
    private final List<SpawnEntity> spawnEntityArrayList;

    public MapEntity(int id, MapDefinition mapDefinition, MapInfoDefinition mapInfoDefinition, MapDatabaseEntity mapDatabaseEntity) {
        this.id = id;
        this.mapDefinition = mapDefinition;
        this.mapInfoDefinition = mapInfoDefinition;
        this.mapDatabaseEntity = mapDatabaseEntity;

        ArrayList<SpawnEntity> spawnEntityArrayList = new ArrayList<>();
        for (MapSpawnEntryDefinition spawnEntryDefinition : mapInfoDefinition.getMapSpawnListDefinition().getSpawns()) {
            spawnEntityArrayList.add(new SpawnEntity(spawnEntryDefinition));
        }

        this.spawnEntityArrayList = Collections.unmodifiableList(spawnEntityArrayList);
    }

    public int getId() {
        return id;
    }

    public TileEntity getTileAt(int x, int y) {
        return new TileEntity(mapDefinition.getTileDefinitionAt(x, y));
    }

    public int getSpawnTime() {
        return mapInfoDefinition.getMapSpawnListDefinition().getTimer();
    }

    public int getMaximumSpawnedMonsterCount() {
        return mapInfoDefinition.getMapSpawnListDefinition().getAlive();
    }

    public int getSpawnedMonsterCount() {
        return mapDatabaseEntity.getSpawns().size();
    }

    public List<SpawnEntity> getSpawnList() {
        return spawnEntityArrayList;
    }

    public void spawnMonster(int id, int x, int y) {
        System.out.println("New spawn! " + id + " - " + x + "/" + y);
    }
}
