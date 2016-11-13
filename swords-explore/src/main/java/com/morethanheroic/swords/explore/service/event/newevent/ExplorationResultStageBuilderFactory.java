package com.morethanheroic.swords.explore.service.event.newevent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExplorationResultStageBuilderFactory {

    private final ExplorationResultBuilderFactory explorationResultBuilderFactory;

    public ExplorationResultStageBuilder newBuilder() {
        return new ExplorationResultStageBuilder(explorationResultBuilderFactory);
    }
}
