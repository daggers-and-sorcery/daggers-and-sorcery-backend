package com.morethanheroic.swords.explore.domain.event.result.impl.option;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EventOption {

    private final String text;
    private final int optionId;
}
