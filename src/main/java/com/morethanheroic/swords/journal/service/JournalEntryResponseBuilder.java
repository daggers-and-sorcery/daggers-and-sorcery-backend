package com.morethanheroic.swords.journal.service;

import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.item.service.response.ItemEntryResponseBuilder;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.monster.service.response.MonsterEntryResponseBuilder;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalEntryResponseBuilder {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    private ItemEntryResponseBuilder itemEntryResponseBuilder;

    @Autowired
    private MonsterEntryResponseBuilder monsterEntryResponseBuilder;

    public Response build(UserEntity userEntity, JournalType journalType, int journalId) {
        Response response = responseFactory.newResponse(userEntity);

        switch (journalType) {
            case ITEM:
                response.setData("journal_entry", itemEntryResponseBuilder.buildItemEntry(itemDefinitionCache.getDefinition(journalId)));
                break;
            case MONSTER:
                response.setData("journal_entry", monsterEntryResponseBuilder.buildMonsterEntry(monsterDefinitionCache.getMonsterDefinition(journalId)));
                break;
            default:
                throw new IllegalArgumentException("Invalid journal type!");
        }

        return response;
    }

    public Response buildInvalidRequest(UserEntity userEntity) {
        Response response = responseFactory.newResponse(userEntity);

        response.setData("journal_entry", "Unavailable!");

        return response;
    }
}
