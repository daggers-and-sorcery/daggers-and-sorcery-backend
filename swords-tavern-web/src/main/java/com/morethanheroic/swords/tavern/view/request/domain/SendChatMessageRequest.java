package com.morethanheroic.swords.tavern.view.request.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class SendChatMessageRequest {

    @Size(min = 1, max = 254)
    private String chatMessage;
}
