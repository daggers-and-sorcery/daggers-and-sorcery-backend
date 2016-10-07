package com.morethanheroic.swords.explore.service;

import com.morethanheroic.swords.explore.service.event.ExplorationEventHandler;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventLocation;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import lombok.extern.log4j.Log4j;
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

    private final Random random;
    private final Map<ExplorationEventLocation, List<ExplorationEventHandler>> locationMap;

    private ExplorationEventChooser(final Random random, final ExplorationEventDefinitionCache explorationEventDefinitionCache, final List<ExplorationEventHandler> explorationEventHandlers) {
        this.random = random;

        final Map<ExplorationEventLocation, List<ExplorationEventHandler>> result = new HashMap<>();

        for (ExplorationEventLocation explorationEventLocation : ExplorationEventLocation.values()) {
            result.put(explorationEventLocation, new ArrayList<>());
        }

        for (ExplorationEventHandler explorationEventHandler : explorationEventHandlers) {
            result.get(explorationEventDefinitionCache.getDefinition(explorationEventHandler.getId()).getLocation()).add(explorationEventHandler);
        }

        locationMap = Collections.unmodifiableMap(result);
    }

    public ExplorationEventHandler getEvent(final ExplorationEventLocation locationType) {
        final List<ExplorationEventHandler> locationDefinitionInfo = locationMap.get(locationType);
        final ExplorationEventHandler result = locationDefinitionInfo.get(random.nextInt(locationDefinitionInfo.size()));

        log.info("Choosen exploration event is " + result.getId());

        return result;
    }
}
