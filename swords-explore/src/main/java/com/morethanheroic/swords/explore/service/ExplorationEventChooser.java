package com.morethanheroic.swords.explore.service;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationAssignmentContext;
import com.morethanheroic.swords.metadata.domain.entity.NumericMetadataEntity;
import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.zone.domain.ZoneDefinition;
import com.morethanheroic.swords.zone.service.definition.cache.ZoneDefinitionCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

@Slf4j
@Service
public class ExplorationEventChooser {

    private static final String ACCESSIBILITY_METADATA_ID_PREFIX = "ZONE_ACCESSIBILITY_";

    private final Random random;
    private final ZoneDefinitionCache zoneDefinitionCache;
    private final Map<ExplorationZone, List<ExplorationEventHandler>> locationMap;
    private final MetadataEntityFactory metadataEntityFactory;

    private ExplorationEventChooser(final Random random, final ZoneDefinitionCache zoneDefinitionCache, final MetadataEntityFactory metadataEntityFactory, final ExplorationEventDefinitionCache explorationEventDefinitionCache, final List<ExplorationEventHandler> explorationEventHandlers) {
        this.random = random;
        this.zoneDefinitionCache = zoneDefinitionCache;
        this.metadataEntityFactory = metadataEntityFactory;

        final Map<ExplorationZone, List<ExplorationEventHandler>> result = Arrays.stream(ExplorationZone.values())
                .collect(collectingAndThen(Collectors.toMap(Function.identity(), explorationEventLocation -> new ArrayList<ExplorationEventHandler>()), ImmutableMap::copyOf));

        for (ExplorationEventHandler explorationEventHandler : explorationEventHandlers) {
            if (explorationEventDefinitionCache.isDefinitionExists(explorationEventHandler.getId())) {
                final ExplorationEventDefinition explorationEventDefinition = explorationEventDefinitionCache.getDefinition(explorationEventHandler.getId());

                final List<ExplorationEventHandler> targetEventList = result.get(explorationEventDefinition.getLocation());
                for (int i = 0; i < explorationEventDefinition.getRarity().getChance(); i++) {
                    targetEventList.add(explorationEventHandler);
                }
            } else {
                throw new IllegalStateException("No definition exists for exploration event handler: " + explorationEventHandler.getId());
            }
        }

        locationMap = Collections.unmodifiableMap(result);
    }

    public ExplorationEventHandler getEvent(final ExplorationZone explorationZone, final ExplorationAssignmentContext explorationAssignmentContext) {
        final ZoneDefinition zoneDefinition = zoneDefinitionCache.getDefinition(explorationZone);

        if (zoneDefinition.getAccessibility().isPresent()) {
            final NumericMetadataEntity accessibilityMetadataEntity = metadataEntityFactory.getNumericEntity(explorationAssignmentContext.getUserEntity(), ACCESSIBILITY_METADATA_ID_PREFIX + explorationZone.name());

            if (accessibilityMetadataEntity.getValue() >= zoneDefinition.getAccessibility().get().getCount()) {
                throw new IllegalStateException("Already used up all chances for zone: " + zoneDefinition.getId().name());
            } else {
                accessibilityMetadataEntity.setValue(accessibilityMetadataEntity.getValue() + 1);
            }
        }

        ExplorationEventHandler result = getRandomEventOnLocation(explorationZone);

        while (!result.shouldAssign(explorationAssignmentContext)) {
            result = getRandomEventOnLocation(explorationZone);
        }

        log.info("Chosen exploration event is " + result.getId());

        return result;
    }

    private ExplorationEventHandler getRandomEventOnLocation(final ExplorationZone locationType) {
        final List<ExplorationEventHandler> locationDefinitionInfo = locationMap.get(locationType);

        return locationDefinitionInfo.get(random.nextInt(locationDefinitionInfo.size()));
    }
}
