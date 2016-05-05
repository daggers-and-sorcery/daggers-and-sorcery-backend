package com.morethanheroic.swords.explore.service;

import com.morethanheroic.swords.explore.service.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ExplorationEventChooser {

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    @Autowired
    private Random random;

    public ExplorationEventDefinition getEvent() {
        return explorationEventDefinitionCache.getDefinition(getRandomExplorationEventId());
    }

    private int getRandomExplorationEventId() {
        return random.nextInt(explorationEventDefinitionCache.getSize()) + 1;
    }
}
