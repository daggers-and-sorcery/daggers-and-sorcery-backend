package com.morethanheroic.swords.explore.service;

import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventLocation;
import com.morethanheroic.swords.explore.service.event.ExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
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
            if (explorationEventDefinitionCache.isDefinitionExists(explorationEventHandler.getId())) {
                final ExplorationEventDefinition explorationEventDefinition = explorationEventDefinitionCache.getDefinition(explorationEventHandler.getId());

                final List<ExplorationEventHandler> targetEventList = result.get(explorationEventDefinition.getLocation());
                for(int i = 0; i < explorationEventDefinition.getRarity().getChance(); i++) {
                    targetEventList.add(explorationEventHandler);
                }
            } else {
                throw new IllegalStateException("No definition exists for exploration event handler: " + explorationEventHandler.getId());
            }
        }

        locationMap = Collections.unmodifiableMap(result);
    }

    public ExplorationEventHandler getEvent(final ExplorationEventLocation locationType) {
        final List<ExplorationEventHandler> locationDefinitionInfo = locationMap.get(locationType);
        final ExplorationEventHandler result = locationDefinitionInfo.get(random.nextInt(locationDefinitionInfo.size()));

        log.info("Chosen exploration event is " + result.getId());

        return result;
    }
}
