package com.morethanheroic.swords.tavern.view.response.chat.list.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ChatEntityPartialResponse extends PartialResponse {

    private String user;
    private String message;
    private Date writingTime;
}
