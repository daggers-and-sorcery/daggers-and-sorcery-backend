package com.morethanheroic.swords.spell.service.page.entry;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.item.service.response.UnidentifiedItemEntryResponseBuilder;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.spell.service.page.PageEntryDataContainer;
import com.morethanheroic.swords.spell.service.page.SpellPageEntry;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class LesserIdentificationPageEntry extends SpellPageEntry {

    private final InventoryFacade inventoryFacade;
    private final ResponseFactory responseFactory;
    private final ItemDefinitionCache itemDefinitionCache;
    private final UnidentifiedItemEntryResponseBuilder unidentifiedItemEntryResponseBuilder;
    private final UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @Override
    public CharacterRefreshResponse build(PageEntryDataContainer pageEntryDataContainer) {
        final CharacterRefreshResponse response = responseFactory.newResponse(pageEntryDataContainer.getUserEntity());

        InventoryEntity inventory = inventoryFacade.getInventory(pageEntryDataContainer.getUserEntity());
        List<InventoryItem> unidentifiedItems = inventory.getItems(IdentificationType.UNIDENTIFIED);

        response.setData("itemlist", convertToResponseItem(pageEntryDataContainer.getSessionEntity(), unidentifiedItems));

        return response;
    }

    private List<Map<String, Object>> convertToResponseItem(SessionEntity httpSession, List<InventoryItem> unidentifiedItems) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (InventoryItem itemDatabaseEntity : unidentifiedItems) {
            Map<String, Object> itemdata = new HashMap<>();

            itemdata.put("itemId", unidentifiedItemIdCalculator.getUnidentifiedItemId(httpSession, itemDatabaseEntity.getItem().getId()));
            itemdata.put("definition", unidentifiedItemEntryResponseBuilder.buildItemEntry(itemDefinitionCache.getDefinition(itemDatabaseEntity.getItem().getId())));

            result.add(itemdata);
        }

        return result;
    }
}
