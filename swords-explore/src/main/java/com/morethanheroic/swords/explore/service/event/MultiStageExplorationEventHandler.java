package com.morethanheroic.swords.explore.service.event;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * A {@link ExplorationEventHandler} implementation that's able to have more than one stage for events.
 *
 * @deprecated Use {@link ImprovedExplorationEventHandler} instead.
 */
public abstract class MultiStageExplorationEventHandler extends ExplorationEventHandler {

    public abstract ExplorationResult explore(UserEntity userEntity, int stage);

    public abstract ExplorationResult info(UserEntity userEntity, int stage);

    public abstract boolean isValidNextStageAtStage(int stage, int nextStage);
}
