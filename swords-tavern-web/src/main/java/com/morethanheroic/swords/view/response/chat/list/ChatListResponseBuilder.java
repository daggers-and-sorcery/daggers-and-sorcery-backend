package com.morethanheroic.swords.view.response.chat.list;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.view.response.chat.list.domain.ChatListResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatListResponseBuilder implements ResponseBuilder<ChatListResponseBuilderConfiguration> {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ChatEntryPartialResponseBuilder chatEntryPartialResponseBuilder;

    @Override
    public Response build(ChatListResponseBuilderConfiguration chatListResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(chatListResponseBuilderConfiguration.getUserEntity());

        response.setData("messages", chatEntryPartialResponseBuilder.build(chatListResponseBuilderConfiguration));

        return response;
    }
}
