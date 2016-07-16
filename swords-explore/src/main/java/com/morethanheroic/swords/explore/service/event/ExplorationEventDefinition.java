package com.morethanheroic.swords.explore.service.event;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.user.domain.UserEntity;

public abstract class ExplorationEventDefinition {

    public abstract int getId();

    public abstract ExplorationEventLocationType getLocation();

    public abstract ExplorationResult explore(UserEntity userEntity);
}
