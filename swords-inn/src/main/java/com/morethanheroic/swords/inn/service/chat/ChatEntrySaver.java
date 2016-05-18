package com.morethanheroic.swords.inn.service.chat;

import com.morethanheroic.swords.inn.domain.chat.ChatEntry;
import com.morethanheroic.swords.inn.repository.dao.ChatDatabaseEntry;
import com.morethanheroic.swords.inn.repository.domain.ChatEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatEntrySaver {

    @Autowired
    private ChatEntryRepository chatEntryRepository;

    public void saveMessage(ChatEntry chatEntry) {
        final ChatDatabaseEntry chatDatabaseEntry = new ChatDatabaseEntry();

        chatDatabaseEntry.setUserId(chatEntry.getUserEntity().getId());
        chatDatabaseEntry.setMessage(chatEntry.getMessage());

        chatEntryRepository.saveEntry(chatDatabaseEntry);
    }
}
