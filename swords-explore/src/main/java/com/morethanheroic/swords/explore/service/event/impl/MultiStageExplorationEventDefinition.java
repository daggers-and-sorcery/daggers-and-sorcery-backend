package com.morethanheroic.swords.explore.service.event.impl;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

public abstract class MultiStageExplorationEventDefinition extends ExplorationEventDefinition {

    public abstract ExplorationResult explore(UserEntity userEntity, int stage);
}
