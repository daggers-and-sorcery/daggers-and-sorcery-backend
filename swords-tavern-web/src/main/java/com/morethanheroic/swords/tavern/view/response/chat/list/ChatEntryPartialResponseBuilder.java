package com.morethanheroic.swords.tavern.view.response.chat.list;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.tavern.view.response.chat.list.domain.ChatEntityPartialResponse;
import com.morethanheroic.swords.tavern.view.response.chat.list.domain.ChatListResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ChatEntryPartialResponseBuilder implements PartialResponseCollectionBuilder<ChatListResponseBuilderConfiguration> {

    @Override
    public Collection<? extends PartialResponse> build(ChatListResponseBuilderConfiguration chatListResponseBuilderConfiguration) {
        return chatListResponseBuilderConfiguration.getChatEntries().stream()
                .map(chatEntry ->
                        ChatEntityPartialResponse.builder()
                                .user(chatEntry.getUserEntity().getUsername())
                                .message(chatEntry.getMessage())
                                .writingTime(chatEntry.getWritingTime())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
