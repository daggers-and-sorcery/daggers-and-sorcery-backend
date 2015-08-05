package com.morethanheroic.swords.map.domain;

import com.morethanheroic.swords.map.repository.dao.MapObjectMapper;
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
    private final MapObjectMapper mapObjectMapper;

    public MapEntity(MapDefinition mapDefinition, MapInfoDefinition mapInfoDefinition, MapDatabaseEntity mapDatabaseEntity, MapObjectMapper mapObjectMapper) {
        this.mapDefinition = mapDefinition;
        this.mapInfoDefinition = mapInfoDefinition;
        this.mapDatabaseEntity = mapDatabaseEntity;
        this.mapObjectMapper = mapObjectMapper;

        ArrayList<SpawnEntity> spawnEntityArrayList = new ArrayList<>();
        for (MapSpawnEntryDefinition spawnEntryDefinition : mapInfoDefinition.getMapSpawnListDefinition().getSpawns()) {
            spawnEntityArrayList.add(new SpawnEntity(spawnEntryDefinition));
        }

        this.spawnEntityArrayList = Collections.unmodifiableList(spawnEntityArrayList);
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
        return mapObjectMapper.getSpawnsForMap(mapDefinition.getId()).size();
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
        mapObjectMapper.saveSpawn(position.getX(), position.getY(), mapDefinition.getId(), MapObjectType.MONSTER, id);
    }

    public List<MapObjectDatabaseEntity> getSpawns() {
        return mapObjectMapper.getSpawnsForMap(mapDefinition.getId());
    }

    public List<MapObjectDatabaseEntity> getSpawnsAt(int x, int y) {
        List<MapObjectDatabaseEntity> spawnAtPointList = new ArrayList<>();

        List<MapObjectDatabaseEntity> spawnList = getSpawns();

        for (MapObjectDatabaseEntity entity : spawnList) {
            if(entity.getX() == x && entity.getY() == y) {
                spawnAtPointList.add(entity);
            }
        }

        return spawnAtPointList;
    }

    public boolean hasSpawnAt(int x, int y, int monsterId) {
        List<MapObjectDatabaseEntity> spawnList = getSpawns();

        for (MapObjectDatabaseEntity entity : spawnList) {
            if(entity.getX() == x && entity.getY() == y && entity.getObject() == monsterId && entity.getType() == MapObjectType.MONSTER) {
                return true;
            }
        }

        return false;
    }
}
