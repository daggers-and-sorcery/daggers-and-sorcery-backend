package com.morethanheroic.swords.map.service;

import com.morethanheroic.swords.map.domain.MapEntity;
import com.morethanheroic.swords.map.service.task.SpawnTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Lazy(false)
public class SpawnScheduler {

    @Autowired
    private MapManager mapManager;

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

    @PostConstruct
    public void init() {
        List<MapEntity> maps = mapManager.getMapList();

        for (MapEntity map : maps) {
            executor.scheduleAtFixedRate(new SpawnTask(map), map.getSpawnTime(), map.getSpawnTime(), TimeUnit.SECONDS);
        }
    }
}
