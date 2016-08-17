package com.morethanheroic.swords.combat.view.response.service.usable;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsableItemsResponseBuilder {

    private final ResponseFactory responseFactory;

    public Response build(UserEntity userEntity, List<InventoryItem> inventoryItems) {
        final Response response = responseFactory.newResponse(userEntity);

        final List<Map<String, Object>> itemsResult = new ArrayList<>();
        for (InventoryItem inventoryItem : inventoryItems) {
            final ItemDefinition itemDefinition = inventoryItem.getItem();

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
