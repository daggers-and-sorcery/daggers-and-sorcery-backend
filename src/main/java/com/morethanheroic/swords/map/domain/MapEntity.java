package com.morethanheroic.swords.map.domain;

import com.morethanheroic.swords.map.repository.domain.MapDatabaseEntity;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.map.repository.domain.MapObjectType;
import com.morethanheroic.swords.map.service.domain.MapDefinition;
import com.morethanheroic.swords.map.service.domain.MapInfoDefinition;
import com.morethanheroic.swords.map.service.domain.MapSpawnEntryDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MapEntity {

    private final MapDefinition mapDefinition;
    private final MapInfoDefinition mapInfoDefinition;
    private final MapDatabaseEntity mapDatabaseEntity;
    private final List<SpawnEntity> spawnEntityArrayList;
    private final Random random = new Random();
    private final List<MapObjectDatabaseEntity> spawnList;

    public MapEntity(MapDefinition mapDefinition, MapInfoDefinition mapInfoDefinition, MapDatabaseEntity mapDatabaseEntity, List<MapObjectDatabaseEntity> spawnList) {
        this.mapDefinition = mapDefinition;
        this.mapInfoDefinition = mapInfoDefinition;
        this.mapDatabaseEntity = mapDatabaseEntity;

        ArrayList<SpawnEntity> spawnEntityArrayList = new ArrayList<>();
        System.out.println(mapInfoDefinition.getMapSpawnListDefinition().getSpawns());
        for (MapSpawnEntryDefinition spawnEntryDefinition : mapInfoDefinition.getMapSpawnListDefinition().getSpawns()) {
            spawnEntityArrayList.add(new SpawnEntity(spawnEntryDefinition));
        }

        this.spawnEntityArrayList = Collections.unmodifiableList(spawnEntityArrayList);
        this.spawnList = spawnList;
    }

    public int getId() {
        return mapDefinition.getId();
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
        return spawnList.size();
    }

    public List<SpawnEntity> getSpawnList() {
        return spawnEntityArrayList;
    }

    public TileEntity getRandomWalkableTile() {
        TileEntity tileEntity = null;

        while (tileEntity== null || !tileEntity.isWalkable()) {
            tileEntity = getTileAt(random.nextInt(mapDefinition.getWidth()), random.nextInt(mapDefinition.getHeight()));
        }

        return tileEntity;
    }

    public void spawnMonster(int id, TileEntity position) {
        spawnList.add(new MapObjectDatabaseEntity(id, mapDefinition.getId(), position.getX(), position.getY(), MapObjectType.MONSTER));
    }

    public List<MapObjectDatabaseEntity> getSpawns() {
        return spawnList;
    }
}
