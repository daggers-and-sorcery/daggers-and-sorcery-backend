package com.morethanheroic.swords.explore.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventDefinition;
import org.springframework.stereotype.Service;

@Service
public class ExplorationEventDefinitionCache implements DefinitionCache<Integer, ExplorationEventDefinition> {

    @Override
    public ExplorationEventDefinition getDefinition(Integer key) {
        return null;
    }
}
