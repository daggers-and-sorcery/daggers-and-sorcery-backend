package com.morethanheroic.swords.explore.service.event.newevent;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ImprovedExplorationEventHandler extends MultiStageExplorationEventHandler {

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    @Override
    @Deprecated
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        return handleExplore(
                ExplorationContext.builder()
                        .userEntity(userEntity)
                        .event(explorationEventDefinitionCache.getDefinition(getId()))
                        .stage(stage)
                        .build()
        );
    }

    @Override
    @Deprecated
    public ExplorationResult info(UserEntity userEntity, int stage) {
        return handleInfo(
                ExplorationContext.builder()
                        .userEntity(userEntity)
                        .event(explorationEventDefinitionCache.getDefinition(getId()))
                        .stage(stage)
                        .build()
        );
    }

    @Override
    @Deprecated
    public ExplorationResult explore(UserEntity userEntity) {
        return handleExplore(
                ExplorationContext.builder()
                        .userEntity(userEntity)
                        .event(explorationEventDefinitionCache.getDefinition(getId()))
                        .stage(0)
                        .build()
        );
    }

    public abstract ExplorationResult handleExplore(final ExplorationContext explorationContext);

    public abstract ExplorationResult handleInfo(final ExplorationContext explorationContext);
}
