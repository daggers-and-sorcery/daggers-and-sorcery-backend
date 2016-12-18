package com.morethanheroic.swords.inn.service.chat.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.inn.domain.chat.ChatEntry;
import com.morethanheroic.swords.inn.repository.dao.ChatDatabaseEntry;
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
