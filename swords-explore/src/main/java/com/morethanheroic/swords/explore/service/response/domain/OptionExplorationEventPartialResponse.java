package com.morethanheroic.swords.explore.service.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.explore.domain.event.result.impl.option.EventOption;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OptionExplorationEventPartialResponse extends PartialResponse {

    private final ExplorationEventResponseType eventType = ExplorationEventResponseType.OPTION;

    private final List<EventOption> eventOptions;
}
