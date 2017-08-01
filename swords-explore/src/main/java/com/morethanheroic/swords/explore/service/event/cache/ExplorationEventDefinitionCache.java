package com.morethanheroic.swords.explore.service.event.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.loader.ExplorationEventDefinitionLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.*;

/**
 * Holds the loaded {@link ExplorationEventDefinition}s.
 */
@Slf4j
@Service
public class ExplorationEventDefinitionCache implements DefinitionCache<Integer, ExplorationEventDefinition> {

    private final Map<Integer, ExplorationEventDefinition> eventDefinitionMap;

    public ExplorationEventDefinitionCache(final ExplorationEventDefinitionLoader explorationEventDefinitionLoader) {
        final List<ExplorationEventDefinition> explorationEventDefinitions = explorationEventDefinitionLoader.loadDefinitions();

        log.info("Loaded " + explorationEventDefinitions.size() + " exploration event definitions.");

        eventDefinitionMap = explorationEventDefinitions.stream()
                .collect(Collectors.toMap(ExplorationEventDefinition::getId, Function.identity()));
    }

    @Override
    public ExplorationEventDefinition getDefinition(Integer key) {
        return eventDefinitionMap.get(key);
    }

    @Override
    public int getSize() {
        return eventDefinitionMap.size();
    }

    @Override
    public List<ExplorationEventDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(eventDefinitionMap.values()));
    }

    @Override
    public boolean isDefinitionExists(Integer key) {
        return eventDefinitionMap.containsKey(key);
    }
}
