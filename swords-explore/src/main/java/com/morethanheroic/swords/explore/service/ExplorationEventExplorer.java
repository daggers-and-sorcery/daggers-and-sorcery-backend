package com.morethanheroic.swords.explore.service;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.context.ExplorationContextFactory;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create and explore an event if the user doesn't have any event running, or run the event to the next stage is the user
 * has an event already in progress.
 */
@Service
public class ExplorationEventExplorer {

    private static final int NOT_STARTED = 0;
    private static final int NO_EVENT = 0;
    private static final int MINIMUM_MOVEMENT_POINTS = 0;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private ExplorationContextFactory explorationContextFactory;

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    @Autowired
    private UserBasicAttributeManipulator basicAttributeManipulator;

    @Transactional
    public ExplorationResult exploreNext(final UserEntity userEntity, final SessionEntity sessionEntity) {
        return explore(userEntity, sessionEntity, explorationEventDefinitionCache.getDefinition(userEntity.getActiveExplorationEvent()).getLocation(), userEntity.getActiveExplorationState());
    }

    @Transactional
    public ExplorationResult explore(final UserEntity userEntity, final SessionEntity sessionEntity, final ExplorationEventLocationType location, final int nextState) {
        if (!canExplore(userEntity, nextState)) {
            return buildFailedExplorationResult();
        }

        if (nextState == 0) {
            basicAttributeManipulator.decreaseMovement(userEntity, 1);
        }

        return buildSuccessfulExplorationResult(userEntity, explorationContextFactory.newExplorationContext(userEntity, sessionEntity, location, nextState));
    }

    private boolean canExplore(final UserEntity userEntity, int nextState) {
        if (userEntity.getMovementPoints() <= MINIMUM_MOVEMENT_POINTS) {
            return false;
        }

        if (nextState > NOT_STARTED && userEntity.getActiveExplorationEvent() == NO_EVENT) {
            return false;
        }

        if (nextState > NOT_STARTED && !((MultiStageExplorationEventDefinition) explorationEventDefinitionCache.getDefinition(userEntity.getActiveExplorationEvent())).isValidNextStageAtStage(userEntity.getActiveExplorationState(), nextState)) {
            return false;
        }

        return true;
    }

    private ExplorationResult buildFailedExplorationResult() {
        return explorationResultFactory.newExplorationResult()
                .addEventEntryResult(
                        TextExplorationEventEntryResult.builder()
                                .content("You feel too tired to explore, you need more than zero movement points.")
                                .build()
                );
    }

    private ExplorationResult buildSuccessfulExplorationResult(final UserEntity userEntity, final ExplorationContext explorationContext) {
        if (explorationContext.getStage() == NO_EVENT) {
            return exploreNewEvent(userEntity, explorationContext);
        }

        return continueEvent(userEntity, explorationContext);
    }

    private ExplorationResult exploreNewEvent(final UserEntity userEntity, final ExplorationContext explorationContext) {
        return explorationContext.getEvent().explore(userEntity);
    }

    private ExplorationResult continueEvent(final UserEntity userEntity, final ExplorationContext explorationContext) {
        return ((MultiStageExplorationEventDefinition) explorationContext.getEvent()).explore(userEntity, explorationContext.getStage());
    }
}
