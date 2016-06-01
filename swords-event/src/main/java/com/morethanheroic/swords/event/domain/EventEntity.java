package com.morethanheroic.swords.event.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@Builder
@ToString
public class EventEntity {

    private int eventId;
    private UserEntity user;
    private Date ending;
}
