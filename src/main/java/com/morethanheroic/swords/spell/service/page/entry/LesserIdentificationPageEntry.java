package com.morethanheroic.swords.spell.service.page.entry;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.item.service.response.UnidentifiedItemEntryResponseBuilder;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.spell.service.page.PageEntryDataContainer;
import com.morethanheroic.swords.spell.service.page.SpellPageEntry;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LesserIdentificationPageEntry extends SpellPageEntry {

    private final InventoryFacade inventoryFacade;
    private final ResponseFactory responseFactory;
    private final ItemDefinitionCache itemDefinitionCache;
    private final UnidentifiedItemEntryResponseBuilder unidentifiedItemEntryResponseBuilder;
    private final UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    public LesserIdentificationPageEntry(InventoryFacade inventoryFacade, ResponseFactory responseFactory, ItemDefinitionCache itemDefinitionCache, UnidentifiedItemEntryResponseBuilder unidentifiedItemEntryResponseBuilder, UnidentifiedItemIdCalculator unidentifiedItemIdCalculator) {
        this.inventoryFacade = inventoryFacade;
        this.responseFactory = responseFactory;
        this.itemDefinitionCache = itemDefinitionCache;
        this.unidentifiedItemEntryResponseBuilder = unidentifiedItemEntryResponseBuilder;
        this.unidentifiedItemIdCalculator = unidentifiedItemIdCalculator;
    }

    @Override
    public CharacterRefreshResponse build(PageEntryDataContainer pageEntryDataContainer) {
        CharacterRefreshResponse response = responseFactory.newResponse(pageEntryDataContainer.getUserEntity());
        InventoryEntity inventory = inventoryFacade.getInventory(pageEntryDataContainer.getUserEntity());
        List<ItemDatabaseEntity> unidentifiedItems = inventory.getItems(false);

        response.setData("itemlist", convertToResponseItem(pageEntryDataContainer.getHttpSession(), unidentifiedItems));

        return response;
    }

    private List<Map<String, Object>> convertToResponseItem(HttpSession httpSession, List<ItemDatabaseEntity> unidentifiedItems) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (ItemDatabaseEntity itemDatabaseEntity : unidentifiedItems) {
            Map<String, Object> itemdata = new HashMap<>();

            itemdata.put("itemId", unidentifiedItemIdCalculator.getUnidentifiedItemId(httpSession, itemDatabaseEntity.getItemId()));
            itemdata.put("definition", unidentifiedItemEntryResponseBuilder.buildItemEntry(itemDefinitionCache.getDefinition(itemDatabaseEntity.getItemId())));

            result.add(itemdata);
        }

        return result;
    }
}
