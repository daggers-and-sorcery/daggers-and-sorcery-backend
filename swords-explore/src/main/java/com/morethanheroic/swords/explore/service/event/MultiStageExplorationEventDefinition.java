package com.morethanheroic.swords.explore.service.event;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

public abstract class MultiStageExplorationEventDefinition extends ExplorationEventDefinition {

    public abstract ExplorationResult explore(UserEntity userEntity, int stage);

    public abstract ExplorationResult info(UserEntity userEntity, int stage);

    public abstract boolean isValidNextStageAtStage(int stage, int nextStage);
}
