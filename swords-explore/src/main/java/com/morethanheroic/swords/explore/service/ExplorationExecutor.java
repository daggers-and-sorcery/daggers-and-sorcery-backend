package com.morethanheroic.swords.explore.service;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.service.context.ExplorationContextFactory;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
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

    @Transactional
    public ExplorationResult explore(final UserEntity userEntity, final SessionEntity sessionEntity) {
        if (canExplore(userEntity)) {
            return buildFailedExplorationResult();
        }

        return buildSuccessfulExplorationResult(userEntity, explorationContextFactory.newExplorationContext(userEntity, sessionEntity));
    }

    @Transactional
    public ExplorationResult explore(final UserEntity userEntity, final SessionEntity sessionEntity, final int state) {
        if (canExplore(userEntity)) {
            return buildFailedExplorationResult();
        }

        return buildSuccessfulExplorationResult(userEntity, explorationContextFactory.newExplorationContext(userEntity, sessionEntity));
    }

    private boolean canExplore(final UserEntity userEntity) {
        return userEntity.getMovementPoints() <= MINIMUM_MOVEMENT_POINTS;
    }

    //TODO: Do a better response than this!
    private ExplorationResult buildFailedExplorationResult() {
        return explorationResultFactory.newExplorationResult();
    }

    private ExplorationResult buildSuccessfulExplorationResult(final UserEntity userEntity, final ExplorationContext explorationContext) {
        return explorationContext.getEvent().explore(userEntity);
    }
}
