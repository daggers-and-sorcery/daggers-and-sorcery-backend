package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.item.domain.ItemType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryItemTypeSorter {

    public Map<ItemType, List<InventoryItem>> sortByType(final List<InventoryItem> inventoryItems) {
        final Map<ItemType, List<InventoryItem>> result = new HashMap<>();

        for (InventoryItem inventoryItem : inventoryItems) {
            final ItemType itemType = inventoryItem.getItem().getType();

            if (!result.containsKey(itemType)) {
                result.put(itemType, new ArrayList<>());
            }

            result.get(itemType).add(inventoryItem);
        }

        return Collections.unmodifiableMap(result);
    }
}
