package com.morethanheroic.swords.explore.domain.context;

import com.morethanheroic.swords.explore.service.event.ExplorationEventHandler;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExplorationContext {

    private final ExplorationEventHandler event;
    private final int stage;
}
