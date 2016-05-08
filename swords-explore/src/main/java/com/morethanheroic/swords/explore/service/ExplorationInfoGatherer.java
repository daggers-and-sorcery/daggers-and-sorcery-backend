package com.morethanheroic.swords.explore.service;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.service.context.ExplorationContextFactory;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create an info response if the user already in a multi-stage exploration event. The info response will contain the
 * options the user can take and a few words about where is he at the moment.
 */
@Service
public class ExplorationInfoGatherer {

    private static final int NO_EVENT = 0;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private ExplorationContextFactory explorationContextFactory;

    @Transactional
    public ExplorationResult info(final UserEntity userEntity, final SessionEntity sessionEntity) {
        if (!hasInfo(userEntity)) {
            return buildEmptyExplorationResult();
        }

        return buildSuccessfulInfoResult(userEntity, createExplorationContext(userEntity, sessionEntity));
    }

    private ExplorationContext createExplorationContext(final UserEntity userEntity, final SessionEntity sessionEntity) {
        return explorationContextFactory.newExplorationContext(userEntity, sessionEntity, userEntity.getActiveExplorationState());
    }

    private boolean hasInfo(final UserEntity userEntity) {
        return userEntity.getActiveExplorationEvent() > NO_EVENT;
    }

    private ExplorationResult buildEmptyExplorationResult() {
        return explorationResultFactory.newExplorationResult();
    }

    private ExplorationResult buildSuccessfulInfoResult(final UserEntity userEntity, final ExplorationContext explorationContext) {
        return ((MultiStageExplorationEventDefinition) explorationContext.getEvent()).info(userEntity, userEntity.getActiveExplorationState());
    }
}
