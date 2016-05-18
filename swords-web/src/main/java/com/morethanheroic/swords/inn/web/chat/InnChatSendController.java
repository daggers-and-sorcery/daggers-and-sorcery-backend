package com.morethanheroic.swords.inn.web.chat;

import com.morethanheroic.swords.inn.domain.chat.ChatEntry;
import com.morethanheroic.swords.inn.request.domain.SendChatMessageRequest;
import com.morethanheroic.swords.inn.service.chat.ChatEntrySaver;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class InnChatSendController {

    @Autowired
    private ChatEntrySaver chatEntrySaver;

    @RequestMapping(value = "/inn/chat/send", method = RequestMethod.POST)
    public void sendNewMessages(UserEntity userEntity, @RequestBody @Valid SendChatMessageRequest sendChatMessageRequest) {
        chatEntrySaver.saveMessage(
                ChatEntry.builder()
                        .userEntity(userEntity)
                        .message(sendChatMessageRequest.getChatMessage())
                        .build()
        );
    }
}
