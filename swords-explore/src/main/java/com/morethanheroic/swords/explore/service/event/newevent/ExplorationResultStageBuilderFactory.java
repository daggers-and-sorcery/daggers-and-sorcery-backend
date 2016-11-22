package com.morethanheroic.swords.explore.service.event.newevent;

import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExplorationResultStageBuilderFactory {

    private final ExplorationResultBuilderFactory explorationResultBuilderFactory;
    private final ExplorationEventDefinitionCache explorationEventDefinitionCache;

    public ExplorationResultStageBuilder newBuilder() {
        return new ExplorationResultStageBuilder(explorationResultBuilderFactory, explorationEventDefinitionCache);
    }
}
