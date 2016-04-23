package com.morethanheroic.swords.settings.service;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsableItemsResponseBuilder {

    private final ResponseFactory responseFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    @Autowired
    public UsableItemsResponseBuilder(ResponseFactory responseFactory, ItemDefinitionCache itemDefinitionCache) {
        this.responseFactory = responseFactory;
        this.itemDefinitionCache = itemDefinitionCache;
    }

    public CharacterRefreshResponse build(UserEntity userEntity, List<JournalDatabaseEntity> journalItems) {
        final CharacterRefreshResponse response = responseFactory.newResponse(userEntity);

        final List<Map<String, Object>> itemsResult = new ArrayList<>();
        for (JournalDatabaseEntity journalEntry : journalItems) {
            final ItemDefinition itemDefinition = itemDefinitionCache.getDefinition(journalEntry.getJournalId());

            if (itemDefinition.isUsable()) {
                final Map<String, Object> itemData = new HashMap<>();

                itemData.put("id", itemDefinition.getId());
                itemData.put("name", itemDefinition.getName());

                itemsResult.add(itemData);
            }
        }

        response.setData("itemList", itemsResult);

        return response;
    }
}
