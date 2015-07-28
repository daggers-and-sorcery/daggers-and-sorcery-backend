package com.morethanheroic.swords.map.service.task;

import com.morethanheroic.swords.map.domain.MapEntity;
import com.morethanheroic.swords.map.domain.TileEntity;

public class SpawnTask implements Runnable {

    private final SpawnChanceCalculator spawnChanceCalculator;
    private final MapEntity mapEntity;

    public SpawnTask(MapEntity map) {
        this.mapEntity = map;
        this.spawnChanceCalculator = new SpawnChanceCalculator(mapEntity.getSpawnList());
    }

    @Override
    public void run() {
        try {
            if (mapEntity.getSpawnedMonsterCount() < mapEntity.getMaximumSpawnedMonsterCount()) {
                int monsterToSpawn = spawnChanceCalculator.getNextMonsterToSpawn();
                TileEntity positionToSpawnOn = mapEntity.getRandomWalkableTile();

                mapEntity.spawnMonster(monsterToSpawn, positionToSpawnOn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
