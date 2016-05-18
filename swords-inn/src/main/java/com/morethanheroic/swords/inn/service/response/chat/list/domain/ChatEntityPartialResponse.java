package com.morethanheroic.swords.inn.service.response.chat.list.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
public class ChatEntityPartialResponse extends PartialResponse {

    private String user;
    private String message;
    private Date writingTime;
}
