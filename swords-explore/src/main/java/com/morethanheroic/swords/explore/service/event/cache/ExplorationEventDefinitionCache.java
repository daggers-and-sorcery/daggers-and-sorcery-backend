package com.morethanheroic.swords.explore.service.event.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.loader.ExplorationEventDefinitionLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds the loaded {@link ExplorationEventDefinition}s.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExplorationEventDefinitionCache implements DefinitionCache<Integer, ExplorationEventDefinition> {

    private final ExplorationEventDefinitionLoader explorationEventDefinitionLoader;

    private Map<Integer, ExplorationEventDefinition> eventDefinitionMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        final List<ExplorationEventDefinition> explorationEventDefinitions = explorationEventDefinitionLoader.loadDefinitions();

        log.info("Loaded " + explorationEventDefinitions.size() + " exploration event definitions.");

        for (ExplorationEventDefinition explorationEventDefinition : explorationEventDefinitions) {
            eventDefinitionMap.put(explorationEventDefinition.getId(), explorationEventDefinition);
        }
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
