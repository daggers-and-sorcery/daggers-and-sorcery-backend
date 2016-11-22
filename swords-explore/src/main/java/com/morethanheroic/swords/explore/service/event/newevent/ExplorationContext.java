package com.morethanheroic.swords.explore.service.event.newevent;

import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExplorationContext {

    private final UserEntity userEntity;
    private final ExplorationEventDefinition event;
    private final int stage;
}
