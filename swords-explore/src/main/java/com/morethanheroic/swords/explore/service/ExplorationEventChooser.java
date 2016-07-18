package com.morethanheroic.swords.explore.service;

import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Log4j
public class ExplorationEventChooser {

    @Autowired
    private Random random;

    private final Map<ExplorationEventLocationType, List<ExplorationEventDefinition>> locationMap;

    @Autowired
    private ExplorationEventChooser(final List<ExplorationEventDefinition> explorationEventDefinitions) {
        final Map<ExplorationEventLocationType, List<ExplorationEventDefinition>> result = new HashMap<>();

        for (ExplorationEventLocationType explorationEventLocationType : ExplorationEventLocationType.values()) {
            result.put(explorationEventLocationType, new ArrayList<>());
        }

        for (ExplorationEventDefinition explorationEventDefinition : explorationEventDefinitions) {
            result.get(explorationEventDefinition.getLocation()).add(explorationEventDefinition);
        }

        locationMap = Collections.unmodifiableMap(result);
    }

    public ExplorationEventDefinition getEvent(final ExplorationEventLocationType locationType) {
        final List<ExplorationEventDefinition> locationDefinitionInfo = locationMap.get(locationType);
        final ExplorationEventDefinition result = locationDefinitionInfo.get(random.nextInt(locationDefinitionInfo.size()));

        log.info("Choosen exploration event is " + result.getId());

        return result;
    }
}
