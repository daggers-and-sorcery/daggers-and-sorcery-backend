package com.morethanheroic.swords.combatsettings.service;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.item.service.domain.ItemDefinition;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UsableItemsResponseBuilder {

    private final ResponseFactory responseFactory;
    private final ItemDefinitionManager itemDefinitionManager;

    @Autowired
    public UsableItemsResponseBuilder(ResponseFactory responseFactory, ItemDefinitionManager itemDefinitionManager) {
        this.responseFactory = responseFactory;
        this.itemDefinitionManager = itemDefinitionManager;
    }

    public Response build(UserEntity userEntity, List<JournalDatabaseEntity> journalItems) {
        Response response = responseFactory.newResponse(userEntity);

        ArrayList<HashMap<String, Object>> itemsResult = new ArrayList<>();
        for(JournalDatabaseEntity journalEntry : journalItems) {
            ItemDefinition itemDefinition = itemDefinitionManager.getItemDefinition(journalEntry.getJournalId());
            HashMap<String, Object> itemData = new HashMap<>();

            itemData.put("id", itemDefinition.getId());
            itemData.put("name", itemDefinition.getName());

            itemsResult.add(itemData);
        }

        response.setData("itemList", itemsResult);

        return response;
    }
}
