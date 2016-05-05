package com.morethanheroic.swords.explore.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ExplorationEventDefinitionCache implements DefinitionCache<Integer, ExplorationEventDefinition> {

    @Autowired
    private List<ExplorationEventDefinition> explorationEventDefinitionList;

    private Map<Integer, ExplorationEventDefinition> explorationEventDefinitions = new HashMap<>();

    @PostConstruct
    private void initialize() {
        for (ExplorationEventDefinition explorationEventDefinition : explorationEventDefinitionList) {
            explorationEventDefinitions.put(explorationEventDefinition.getId(), explorationEventDefinition);
        }

        log.info("Loaded " + explorationEventDefinitions.size() + " exploration event definitions.");
    }

    @Override
    public ExplorationEventDefinition getDefinition(Integer key) {
        return explorationEventDefinitions.get(key);
    }

    @Override
    public int getSize() {
        return explorationEventDefinitions.size();
    }
}
