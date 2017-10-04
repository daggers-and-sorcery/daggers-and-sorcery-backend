package com.morethanheroic.swords.race.service;

import com.morethanheroic.definition.cache.DefinitionCache;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceDefinition;
import com.morethanheroic.swords.race.service.loader.RaceDefinitionLoader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Store the {@link RaceDefinition}es in a cached manner.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RaceDefinitionCache implements DefinitionCache<Race, RaceDefinition> {

    private Map<Race, RaceDefinition> raceEntityMap = new EnumMap<>(Race.class);

    @NonNull
    private final RaceDefinitionLoader raceDefinitionLoader;

    @PostConstruct
    private void initialize() throws IOException {
        final List<RaceDefinition> raceDefinitionList = raceDefinitionLoader.loadDefinitions();

        log.info("Loaded " + raceDefinitionList.size() + " race entity definitions.");

        for (RaceDefinition raceDefinition : raceDefinitionList) {
            raceEntityMap.put(raceDefinition.getRace(), raceDefinition);
        }
    }

    @Override
    public RaceDefinition getDefinition(final Race race) {
        return raceEntityMap.get(race);
    }

    @Override
    public int getSize() {
        return raceEntityMap.size();
    }

    @Override
    public List<RaceDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(raceEntityMap.values()));
    }

    @Override
    public boolean isDefinitionExists(final Race key) {
        return raceEntityMap.containsKey(key);
    }
}
