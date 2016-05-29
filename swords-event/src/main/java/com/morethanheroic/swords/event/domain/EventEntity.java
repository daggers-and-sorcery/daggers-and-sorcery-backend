package com.morethanheroic.swords.event.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class EventEntity {

    private int userId;
    private int eventId;
    private Date ending;
}
