package com.morethanheroic.swords.map.service;

import com.morethanheroic.swords.map.domain.MapEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class SpawnScheduler {

    @Autowired
    private MapManager mapManager;

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

    @PostConstruct
    public void init() {
        List<MapEntity> maps = mapManager.getMapList();

        for (MapEntity map : maps) {
            int time = map.getSpawnTime();

            executor.scheduleAtFixedRate(() -> {
                System.out.println("DO THIS RUN?");
                if (map.getSpawnedMonsterCount() < map.getMaximumSpawnedMonsterCount()) {
                    map.spawnMonster(1,99,99);
                }
            }, time, time, TimeUnit.SECONDS);
        }
    }
}
