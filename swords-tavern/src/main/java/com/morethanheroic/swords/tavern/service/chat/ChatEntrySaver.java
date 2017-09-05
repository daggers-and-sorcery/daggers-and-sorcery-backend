package com.morethanheroic.swords.tavern.service.chat;

import com.morethanheroic.swords.tavern.domain.chat.ChatEntry;
import com.morethanheroic.swords.tavern.repository.dao.ChatDatabaseEntry;
import com.morethanheroic.swords.tavern.repository.domain.ChatEntryRepository;
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
