package com.morethanheroic.swords.inn.service.chat;

import com.morethanheroic.swords.inn.domain.chat.ChatEntry;
import com.morethanheroic.swords.inn.repository.domain.ChatEntryRepository;
import com.morethanheroic.swords.inn.service.chat.transformer.ChatEntryDefinitionListTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatEntityFactory {

    @Autowired
    private ChatEntryRepository chatEntryRepository;

    @Autowired
    private ChatEntryDefinitionListTransformer chatEntryDefinitionListTransformer;

    public List<ChatEntry> getChatEntities() {
        return chatEntryDefinitionListTransformer.transform(chatEntryRepository.getLastTwentyFiveMessage());
    }
}
