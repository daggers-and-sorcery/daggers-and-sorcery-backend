package com.morethanheroic.swords.explore.service;

import static java.util.stream.Collectors.collectingAndThen;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.explore.service.event.ExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationAssignmentContext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExplorationEventChooser {

    private final Random random;
    private final Map<ExplorationZone, List<ExplorationEventHandler>> locationMap;

    private ExplorationEventChooser(final Random random, final ExplorationEventDefinitionCache explorationEventDefinitionCache, final List<ExplorationEventHandler> explorationEventHandlers) {
        this.random = random;

        final Map<ExplorationZone, List<ExplorationEventHandler>> result = Arrays.stream(ExplorationZone.values())
              .collect(collectingAndThen(Collectors.toMap(Function.identity(), explorationEventLocation -> new ArrayList<ExplorationEventHandler>()), ImmutableMap::copyOf));

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

    public ExplorationEventHandler getEvent(final ExplorationZone locationType, final ExplorationAssignmentContext explorationAssignmentContext) {
        ExplorationEventHandler result = getRandomEventOnLocation(locationType);

        while(!result.shouldAssign(explorationAssignmentContext)) {
            result = getRandomEventOnLocation(locationType);
        }

        log.info("Chosen exploration event is " + result.getId());

        return result;
    }

    private ExplorationEventHandler getRandomEventOnLocation(final ExplorationZone locationType) {
        final List<ExplorationEventHandler> locationDefinitionInfo = locationMap.get(locationType);

        return locationDefinitionInfo.get(random.nextInt(locationDefinitionInfo.size()));
    }
}
