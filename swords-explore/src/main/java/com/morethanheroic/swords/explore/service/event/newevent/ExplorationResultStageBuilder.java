package com.morethanheroic.swords.explore.service.event.newevent;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ExplorationResultStageBuilder {

    private final ExplorationResultBuilderFactory explorationResultBuilderFactory;

    private Map<Integer, ExplorationResultRuntime> stageMap = new HashMap<>();

    public ExplorationResultStageBuilder addStage(int stageId, ExplorationResultRuntime runnable) {
        stageMap.put(stageId, runnable);

        return this;
    }

    public ExplorationResult runStage(final ExplorationContext context) {
        return stageMap.get(context.getStage()).run(
                explorationResultBuilderFactory.newExplorationResultBuilder(context.getUserEntity(), context.getEvent())
        );
    }
}
