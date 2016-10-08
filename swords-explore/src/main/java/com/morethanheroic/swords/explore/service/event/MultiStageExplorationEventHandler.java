package com.morethanheroic.swords.explore.service.event;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.user.domain.UserEntity;

public abstract class MultiStageExplorationEventHandler extends ExplorationEventHandler {

    public abstract ExplorationResult explore(UserEntity userEntity, int stage);

    public abstract ExplorationResult info(UserEntity userEntity, int stage);

    public abstract boolean isValidNextStageAtStage(int stage, int nextStage);
}
