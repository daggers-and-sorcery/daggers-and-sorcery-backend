package com.morethanheroic.swords.explore.service;

import com.morethanheroic.swords.explore.service.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ExplorationEventChooser {

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    @Autowired
    private Random random;

    private final Map<ExplorationEventLocationType, Set<ExplorationEventDefinition>> locationMap;

    @Autowired
    private ExplorationEventChooser(final List<ExplorationEventDefinition> explorationEventDefinitions) {
        final Map<ExplorationEventLocationType, Set<ExplorationEventDefinition>> result = new HashMap<>();

        for(ExplorationEventLocationType explorationEventLocationType
                )

        for (ExplorationEventDefinition explorationEventDefinition : explorationEventDefinitions) {
            result.get(explorationEventDefinition.getLocation()).add(explorationEventDefinition);
        }

        locationMap = Collections.unmodifiableMap(result);
    }

    public ExplorationEventDefinition getEvent(ExplorationEventLocationType locationType) {
        return explorationEventDefinitionCache.getDefinition(getRandomExplorationEventId());
    }

    private int getRandomExplorationEventId() {
        return random.nextInt(explorationEventDefinitionCache.getSize()) + 1;
    }
}
