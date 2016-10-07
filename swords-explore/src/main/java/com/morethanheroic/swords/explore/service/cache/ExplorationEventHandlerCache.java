package com.morethanheroic.swords.explore.service.cache;

import com.morethanheroic.swords.explore.service.event.ExplorationEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ExplorationEventHandlerCache {

    private final Map<Integer, ExplorationEventHandler> explorationEventDefinitions;

    public ExplorationEventHandlerCache(final List<ExplorationEventHandler> explorationEventHandlerList) {
        final Map<Integer, ExplorationEventHandler> result = new HashMap<>();

        for (ExplorationEventHandler explorationEventHandler : explorationEventHandlerList) {
            result.put(explorationEventHandler.getId(), explorationEventHandler);
        }

        explorationEventDefinitions = Collections.unmodifiableMap(result);

        log.info("Loaded " + explorationEventDefinitions.size() + " exploration event handlers.");
    }

    public ExplorationEventHandler getHandler(int key) {
        return explorationEventDefinitions.get(key);
    }
}
