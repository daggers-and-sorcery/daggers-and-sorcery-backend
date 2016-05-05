package com.morethanheroic.swords.explore.domain.context;

import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExplorationContext {

    private final ExplorationEventDefinition event;
    private final int stage;
}
