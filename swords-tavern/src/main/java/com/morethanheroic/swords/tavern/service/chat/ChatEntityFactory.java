package com.morethanheroic.swords.tavern.service.chat;

import com.morethanheroic.swords.tavern.domain.chat.ChatEntry;
import com.morethanheroic.swords.tavern.repository.domain.ChatEntryRepository;
import com.morethanheroic.swords.tavern.service.chat.transformer.ChatEntryDefinitionListTransformer;
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
