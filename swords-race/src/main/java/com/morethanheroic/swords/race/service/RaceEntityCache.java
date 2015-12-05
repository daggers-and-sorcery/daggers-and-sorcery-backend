package com.morethanheroic.swords.race.service;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceEntity;
import com.morethanheroic.swords.race.service.loader.RaceEntityLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Store the {@link RaceEntity}es in a cached manner.
 */
@Service
public class RaceEntityCache {

    private Map<Race, RaceEntity> raceEntityMap = new EnumMap<>(Race.class);

    @Autowired
    private RaceEntityLoader raceEntityLoader;

    @PostConstruct
    private void initialize() {
        final List<RaceEntity> raceEntityList = raceEntityLoader.loadRaceEntities();

        for (RaceEntity raceEntity : raceEntityList) {
            raceEntityMap.put(raceEntity.getRace(), raceEntity);
        }
    }

    public RaceEntity getRaceEntity(Race race) {
        return raceEntityMap.get(race);
    }
}
