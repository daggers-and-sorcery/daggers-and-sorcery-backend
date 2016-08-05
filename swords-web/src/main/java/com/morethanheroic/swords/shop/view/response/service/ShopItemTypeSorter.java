package com.morethanheroic.swords.shop.view.response.service;

import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.shop.domain.ShopItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopItemTypeSorter {

    public Map<ItemType, List<ShopItem>> sortByType(final List<ShopItem> shopItems) {
        final Map<ItemType, List<ShopItem>> result = new HashMap<>();

        for (ShopItem shopItem : shopItems) {
            final ItemType itemType = shopItem.getItem().getType();

            if (!result.containsKey(itemType)) {
                result.put(itemType, new ArrayList<>());
            }

            result.get(itemType).add(shopItem);
        }

        return Collections.unmodifiableMap(result);
    }
}
