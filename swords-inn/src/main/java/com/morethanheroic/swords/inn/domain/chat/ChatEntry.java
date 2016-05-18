package com.morethanheroic.swords.inn.domain.chat;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ChatEntry {

    private UserEntity userEntity;
    private String message;
    private LocalDateTime writingTime;
}
