package com.morethanheroic.swords.explore.service.event.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.explore.domain.EventDefinition;
import com.morethanheroic.swords.explore.service.event.loader.EventDefinitionLoader;
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
 * Holds the loaded {@link EventDefinition}s.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EventDefinitionCache implements DefinitionCache<Integer, EventDefinition> {

    private final EventDefinitionLoader eventDefinitionLoader;

    private Map<Integer, EventDefinition> eventDefinitionMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        final List<EventDefinition> eventDefinitions = eventDefinitionLoader.loadDefinitions();

        log.info("Loaded " + eventDefinitions.size() + " item definitions.");

        for (EventDefinition eventDefinition : eventDefinitions) {
            eventDefinitionMap.put(eventDefinition.getId(), eventDefinition);
        }
    }

    @Override
    public EventDefinition getDefinition(Integer key) {
        return eventDefinitionMap.get(key);
    }

    @Override
    public int getSize() {
        return eventDefinitionMap.size();
    }

    @Override
    public List<EventDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(eventDefinitionMap.values()));
    }

    @Override
    public boolean isDefinitionExists(Integer key) {
        return eventDefinitionMap.containsKey(key);
    }
}
