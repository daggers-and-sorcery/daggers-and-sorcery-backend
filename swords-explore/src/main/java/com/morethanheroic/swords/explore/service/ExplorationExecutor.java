package com.morethanheroic.swords.explore.service;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.service.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.context.ExplorationContextFactory;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExplorationExecutor {

    private static final int MINIMUM_MOVEMENT_POINTS = 0;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private ExplorationContextFactory explorationContextFactory;

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    @Transactional
    public ExplorationResult explore(final UserEntity userEntity, final SessionEntity sessionEntity) {
        if (!canExplore(userEntity)) {
            return buildFailedExplorationResult();
        }

        return buildSuccessfulExplorationResult(userEntity, explorationContextFactory.newExplorationContext(userEntity, sessionEntity, 0));
    }

    @Transactional
    public ExplorationResult explore(final UserEntity userEntity, final SessionEntity sessionEntity, final int nextState) {
        if (!canExplore(userEntity, nextState)) {
            return buildFailedExplorationResult();
        }

        return buildSuccessfulExplorationResult(userEntity, explorationContextFactory.newExplorationContext(userEntity, sessionEntity, nextState));
    }

    private boolean canExplore(final UserEntity userEntity) {
        return userEntity.getMovementPoints() > MINIMUM_MOVEMENT_POINTS && userEntity.getActiveExplorationEvent() == 0;
    }

    private boolean canExplore(final UserEntity userEntity, int nextState) {
        return userEntity.getMovementPoints() > MINIMUM_MOVEMENT_POINTS && userEntity.getActiveExplorationEvent() != 0
                && ((MultiStageExplorationEventDefinition) explorationEventDefinitionCache.getDefinition(userEntity.getActiveExplorationEvent())).isValidNextStageAtStage(userEntity.getActiveExplorationState(), nextState);
    }

    //TODO: Do a better response than this!
    private ExplorationResult buildFailedExplorationResult() {
        return explorationResultFactory.newExplorationResult();
    }

    private ExplorationResult buildSuccessfulExplorationResult(final UserEntity userEntity, final ExplorationContext explorationContext) {
        if (explorationContext.getStage() == 0) {
            return explorationContext.getEvent().explore(userEntity);
        }

        return ((MultiStageExplorationEventDefinition) explorationContext.getEvent()).explore(userEntity, explorationContext.getStage());
    }

    @Transactional
    public ExplorationResult info(final UserEntity userEntity, final SessionEntity sessionEntity) {
        if (!canGetInfo(userEntity)) {
            return buildFailedExplorationResult();
        }

        ExplorationContext explorationContext = explorationContextFactory.newExplorationContext(userEntity, sessionEntity, userEntity.getActiveExplorationState());

        return ((MultiStageExplorationEventDefinition) explorationContext.getEvent()).info(userEntity, userEntity.getActiveExplorationState());
    }

    private boolean canGetInfo(final UserEntity userEntity) {
        return userEntity.getActiveExplorationEvent() > 0;
    }
}
