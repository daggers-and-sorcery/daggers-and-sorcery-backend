package com.morethanheroic.swords.map.service.task;

import com.morethanheroic.swords.map.domain.MapEntity;

public class SpawnTask implements Runnable {

    private MapEntity map;

    public SpawnTask(MapEntity map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            if (map.getSpawnedMonsterCount() < map.getMaximumSpawnedMonsterCount()) {
                map.spawnMonster(1, 99, 99);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
