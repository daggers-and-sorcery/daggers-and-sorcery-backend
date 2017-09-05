package com.morethanheroic.swords.tavern.domain.chat;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ChatEntry {

    private UserEntity userEntity;
    private String message;
    private Date writingTime;
}
