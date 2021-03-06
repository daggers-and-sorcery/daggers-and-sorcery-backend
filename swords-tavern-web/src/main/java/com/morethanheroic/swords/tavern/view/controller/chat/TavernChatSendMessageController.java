package com.morethanheroic.swords.tavern.view.controller.chat;

import com.morethanheroic.swords.tavern.domain.chat.ChatEntry;
import com.morethanheroic.swords.tavern.view.request.domain.SendChatMessageRequest;
import com.morethanheroic.swords.tavern.service.chat.ChatEntrySaver;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TavernChatSendMessageController {

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
