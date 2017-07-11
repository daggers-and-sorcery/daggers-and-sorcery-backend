package com.morethanheroic.swords.shop.view.response.service;

import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.service.sorter.InventoryItemTypeSorter;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.domain.ShopItem;
import com.morethanheroic.swords.shop.service.ShopEntityFactory;
import com.morethanheroic.swords.shop.service.availability.ShopAvailabilityCalculator;
import com.morethanheroic.swords.shop.service.price.ItemSellPriceCalculator;
import com.morethanheroic.swords.shop.service.price.domain.ItemPriceCalculationContext;
import com.morethanheroic.swords.shop.view.response.service.sell.ShopSellItem;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Provide a convenient way to build a displayable list of the items in a shop.
 */
@Service
@RequiredArgsConstructor
public class ShopItemListFactory {

    private final ShopItemTypeSorter shopItemTypeSorter;
    private final InventoryItemTypeSorter inventoryItemTypeSorter;
    private final ShopEntityFactory shopEntityFactory;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemSellPriceCalculator itemSellPriceCalculator;
    private final ShopAvailabilityCalculator shopAvailabilityCalculator;

    public Map<ItemType, List<ShopItem>> getItemsToBuyByThePlayerInShop(final UserEntity userEntity, final ShopDefinition shopDefinition) {
        if(!shopAvailabilityCalculator.isAvailable(userEntity, shopDefinition) || !shopDefinition.getAvailableFeatures().isBuying()) {
            return Collections.emptyMap();
        }

        return shopItemTypeSorter.sortByType(shopEntityFactory.getEntity(shopDefinition).getAllItems(userEntity));
    }

    public Map<ItemType, List<ShopSellItem>> getItemsToSellByThePlayerInShop(final UserEntity userEntity, final ShopDefinition shopDefinition) {
        if(!shopAvailabilityCalculator.isAvailable(userEntity, shopDefinition) || !shopDefinition.getAvailableFeatures().isSelling()) {
            return Collections.emptyMap();
        }

        final Map<ItemType, List<InventoryItem>> items = inventoryItemTypeSorter.sortByType(inventoryEntityFactory.getEntity(userEntity).getItems());
        final Map<ItemType, List<ShopSellItem>> result = new HashMap<>();

        for (Map.Entry<ItemType, List<InventoryItem>> item : items.entrySet()) {
            if (item.getKey() == ItemType.MONEY) {
                continue;
            }

            if (!result.containsKey(item.getKey())) {
                result.put(item.getKey(), new ArrayList<>());
            }

            for (InventoryItem inventoryItem : item.getValue()) {
                result.get(item.getKey()).add(
                        ShopSellItem.builder()
                                .inventoryItem(inventoryItem)
                                .sellPrice(itemSellPriceCalculator.calculateSellPrice(
                                        ItemPriceCalculationContext.builder()
                                                .userEntity(userEntity)
                                                .itemDefinition(inventoryItem.getItem())
                                                .build()
                                ))
                                .build()
                );
            }
        }

        return result;
    }
}
