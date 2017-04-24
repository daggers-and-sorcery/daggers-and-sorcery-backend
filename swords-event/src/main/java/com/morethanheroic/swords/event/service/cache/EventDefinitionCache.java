package com.morethanheroic.swords.event.service.cache;

import com.morethanheroic.swords.definition.cache.impl.MapBasedDefinitionCache;
import com.morethanheroic.swords.event.domain.EventDefinition;
import com.morethanheroic.swords.event.service.loader.EventDefinitionLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Holds the loaded {@link EventDefinition}s.
 */
@Slf4j
@Service
public class EventDefinitionCache extends MapBasedDefinitionCache<Integer, EventDefinition> {

    public EventDefinitionCache(final EventDefinitionLoader eventDefinitionLoader) throws IOException {
        super(
                eventDefinitionLoader.loadDefinitions().stream()
                        .collect(Collectors.toMap(EventDefinition::getId, Function.identity()))
        );

        log.info("Loaded " + this.getSize() + " event definitions.");
    }
}
