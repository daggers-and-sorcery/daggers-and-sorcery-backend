package com.morethanheroic.swords.event.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class EventEntity {

    private int eventId;
    private UserEntity user;
    private Date ending;
}
