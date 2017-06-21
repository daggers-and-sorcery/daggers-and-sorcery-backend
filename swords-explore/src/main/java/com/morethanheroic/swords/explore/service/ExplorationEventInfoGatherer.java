package com.morethanheroic.swords.explore.service;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.service.context.ExplorationContextFactory;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventHandler;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create an info response if the user already in a multi-stage exploration event. The info response will contain the
 * options the user can take and a few words about where is he at the moment.
 */
@Service
@RequiredArgsConstructor
public class ExplorationEventInfoGatherer {

    private final ExplorationResultFactory explorationResultFactory;
    private final ExplorationContextFactory explorationContextFactory;

    @Transactional
    public ExplorationResult info(final UserEntity userEntity, final SessionEntity sessionEntity) {
        if (!userEntity.hasActiveExplorationEvent()) {
            return explorationResultFactory.newEmptyExplorationResult();
        }

        return buildSuccessfulInfoResult(userEntity, createExplorationContext(userEntity, sessionEntity));
    }

    private ExplorationContext createExplorationContext(final UserEntity userEntity, final SessionEntity sessionEntity) {
        return explorationContextFactory.newExplorationContext(userEntity, sessionEntity, null, userEntity.getActiveExplorationState());
    }

    private ExplorationResult buildSuccessfulInfoResult(final UserEntity userEntity, final ExplorationContext explorationContext) {
        return ((MultiStageExplorationEventHandler) explorationContext.getEvent()).info(userEntity, userEntity.getActiveExplorationState());
    }
}
