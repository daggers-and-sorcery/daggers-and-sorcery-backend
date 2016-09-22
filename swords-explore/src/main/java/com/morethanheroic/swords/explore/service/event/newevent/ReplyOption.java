package com.morethanheroic.swords.explore.service.event.newevent;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReplyOption {

    private final String message;
    private final int stage;
}
