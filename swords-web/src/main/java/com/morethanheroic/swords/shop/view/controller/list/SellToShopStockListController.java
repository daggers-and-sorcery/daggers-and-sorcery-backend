package com.morethanheroic.swords.shop.view.controller.list;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.service.InventoryItemTypeSorter;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.shop.service.ItemPriceCalculator;
import com.morethanheroic.swords.shop.service.cache.ShopDefinitionCache;
import com.morethanheroic.swords.shop.view.response.domain.sell.configuration.ShopSellListResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.service.sell.ShopSellItem;
import com.morethanheroic.swords.shop.view.response.service.sell.ShopSellListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SellToShopStockListController {

    private final ShopSellListResponseBuilder shopSellListResponseBuilder;
    private final ShopDefinitionCache shopDefinitionCache;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final InventoryItemTypeSorter inventoryItemTypeSorter;
    private final ItemPriceCalculator itemPriceCalculator;

    @RequestMapping(value = "/shop/selllist/{shopId}", method = RequestMethod.GET)
    public Response listStock(UserEntity userEntity, SessionEntity sessionEntity, @PathVariable int shopId) {
        if (!shopDefinitionCache.isDefinitionExists(shopId)) {
            throw new NotFoundException();
        }

        //TODO: Check that the user is in the same city as the shop.

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());
        final int moneyAmount = inventoryEntity.getMoneyAmount(MoneyType.MONEY);

        return shopSellListResponseBuilder.build(
                ShopSellListResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .sessionEntity(sessionEntity)
                        .bronze(moneyAmount % 100)
                        .silver((moneyAmount / 100) % 100)
                        .gold(moneyAmount / 10000)
                        .shopDefinition(shopDefinitionCache.getDefinition(shopId))
                        .items(transform(inventoryItemTypeSorter.sortByType(inventoryEntity.getItems())))
                        .build()
        );
    }

    private Map<ItemType, List<ShopSellItem>> transform(final Map<ItemType, List<InventoryItem>> items) {
        final Map<ItemType, List<ShopSellItem>> result = new HashMap<>();

        for (Map.Entry<ItemType, List<InventoryItem>> item : items.entrySet()) {
            if (item.getKey() == ItemType.MONEY) {
                continue;
            }

            if (!result.containsKey(item.getKey())) {
                result.put(item.getKey(), new ArrayList<>());
            }

            for (InventoryItem inventoryItem : item.getValue()) {
                final ItemDefinition itemDefinition = inventoryItem.getItem();

                result.get(item.getKey()).add(
                        ShopSellItem.builder()
                                .inventoryItem(inventoryItem)
                                .sellPrice(itemPriceCalculator.getSellPrice(itemDefinition))
                                .build()
                );
            }
        }

        return result;
    }
}