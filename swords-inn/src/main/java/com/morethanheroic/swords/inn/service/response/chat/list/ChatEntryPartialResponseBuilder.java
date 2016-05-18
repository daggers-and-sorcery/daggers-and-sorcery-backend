package com.morethanheroic.swords.inn.service.response.chat.list;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.inn.domain.chat.ChatEntry;
import com.morethanheroic.swords.inn.service.response.chat.list.domain.ChatEntityPartialResponse;
import com.morethanheroic.swords.inn.service.response.chat.list.domain.ChatListResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ChatEntryPartialResponseBuilder implements PartialResponseCollectionBuilder<ChatListResponseBuilderConfiguration> {

    @Override
    public Collection<? extends PartialResponse> build(ChatListResponseBuilderConfiguration chatListResponseBuilderConfiguration) {
        final List<PartialResponse> result = new ArrayList<>();

        for (ChatEntry chatEntry : chatListResponseBuilderConfiguration.getChatEntries()) {
            result.add(
                    ChatEntityPartialResponse.builder()
                            .user(chatEntry.getUserEntity().getUsername())
                            .message(chatEntry.getMessage())
                            .writingTime(chatEntry.getWritingTime())
                            .build()
            );
        }

        return result;
    }
}
