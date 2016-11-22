package com.morethanheroic.swords.explore.service.event;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.user.domain.UserEntity;

public abstract class ExplorationEventHandler {

    public abstract int getId();

    public abstract ExplorationResult explore(UserEntity userEntity);
}
