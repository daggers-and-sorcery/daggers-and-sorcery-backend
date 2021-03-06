package com.morethanheroic.swords.tavern.service.chat.transformer;

import com.morethanheroic.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.swords.tavern.domain.chat.ChatEntry;
import com.morethanheroic.swords.tavern.repository.dao.ChatDatabaseEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatEntryDefinitionListTransformer implements DefinitionListTransformer<List<ChatEntry>,
        List<ChatDatabaseEntry>> {

    @Autowired
    private ChatEntryDefinitionTransformer chatEntryDefinitionTransformer;

    @Override
    public List<ChatEntry> transform(List<ChatDatabaseEntry> rawDefinition) {
        final List<ChatEntry> result = new ArrayList<>();

        for (ChatDatabaseEntry chatDatabaseEntry : rawDefinition) {
            result.add(chatEntryDefinitionTransformer.transform(chatDatabaseEntry));
        }

        return result;
    }
}
