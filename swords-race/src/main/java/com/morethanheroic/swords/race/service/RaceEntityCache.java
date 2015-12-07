package com.morethanheroic.swords.race.service;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceEntity;
import com.morethanheroic.swords.race.service.loader.RaceEntityLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

//TODO: Rename the whole bunch to RaceDefinition
/**
 * Store the {@link RaceEntity}es in a cached manner.
 */
@Service
@Slf4j
public class RaceEntityCache {

    private Map<Race, RaceEntity> raceEntityMap = new EnumMap<>(Race.class);

    @Autowired
    private RaceEntityLoader raceEntityLoader;

    @PostConstruct
    private void initialize() throws IOException {
        final List<RaceEntity> raceEntityList = raceEntityLoader.loadRaceEntities();

        log.info("Loaded " + raceEntityList.size() + " race entity definitions.");

        for (RaceEntity raceEntity : raceEntityList) {
            raceEntityMap.put(raceEntity.getRace(), raceEntity);
        }
    }

    public RaceEntity getRaceEntity(Race race) {
        return raceEntityMap.get(race);
    }
}
