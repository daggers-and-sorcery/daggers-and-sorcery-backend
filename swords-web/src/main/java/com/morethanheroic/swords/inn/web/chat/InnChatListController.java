package com.morethanheroic.swords.inn.web.chat;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inn.domain.chat.ChatEntry;
import com.morethanheroic.swords.inn.service.chat.ChatEntityFactory;
import com.morethanheroic.swords.inn.service.response.chat.list.ChatListResponseBuilder;
import com.morethanheroic.swords.inn.service.response.chat.list.domain.ChatListResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InnChatListController {

    @Autowired
    private ChatEntityFactory chatEntityFactory;

    @Autowired
    private ChatListResponseBuilder chatListResponseBuilder;

    @RequestMapping(value = "/inn/chat/list", method = RequestMethod.GET)
    public Response listChatMessages(final UserEntity userEntity) {
        final List<ChatEntry> chatEntries = chatEntityFactory.getChatEntities();

        return chatListResponseBuilder.build(
                ChatListResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .chatEntries(chatEntries)
                        .build()
        );
    }
}
