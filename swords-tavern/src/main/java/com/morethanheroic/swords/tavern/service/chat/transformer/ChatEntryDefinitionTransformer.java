package com.morethanheroic.swords.tavern.service.chat.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.tavern.domain.chat.ChatEntry;
import com.morethanheroic.swords.tavern.repository.dao.ChatDatabaseEntry;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatEntryDefinitionTransformer implements DefinitionTransformer<ChatEntry, ChatDatabaseEntry> {

    @Autowired
    private UserEntityFactory userEntityFactory;

    @Override
    public ChatEntry transform(ChatDatabaseEntry rawDefinition) {
        return ChatEntry.builder()
                .userEntity(userEntityFactory.getEntity(rawDefinition.getUserId()))
                .message(rawDefinition.getMessage())
                .writingTime(rawDefinition.getWritingTime())
                .build();
    }
}
