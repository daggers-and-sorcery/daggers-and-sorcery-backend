package com.morethanheroic.swords.tavern.view.response.chat.list.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.inn.domain.chat.ChatEntry;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ChatListResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private UserEntity userEntity;
    private List<ChatEntry> chatEntries;
}
