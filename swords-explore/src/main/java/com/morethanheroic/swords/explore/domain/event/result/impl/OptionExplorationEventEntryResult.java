package com.morethanheroic.swords.explore.domain.event.result.impl;

import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.option.EventOption;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OptionExplorationEventEntryResult implements ExplorationEventEntryResult {

    private final List<EventOption> options;
}
