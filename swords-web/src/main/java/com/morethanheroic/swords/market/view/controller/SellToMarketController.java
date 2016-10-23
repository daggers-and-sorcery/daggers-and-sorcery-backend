package com.morethanheroic.swords.market.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.service.InventoryItemTypeSorter;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.view.service.ListItemsToSellResponseBuilder;
import com.morethanheroic.swords.market.view.service.ShowItemToSellResponseBuilder;
import com.morethanheroic.swords.market.view.service.domain.ListItemsToSellResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.service.domain.ShowItemToSellResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SellToMarketController {

    private final ShowItemToSellResponseBuilder showItemToSellResponseBuilder;
    private final ItemDefinitionCache itemDefinitionCache;
    private final InventoryItemTypeSorter inventoryItemTypeSorter;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ListItemsToSellResponseBuilder listItemsToSellResponseBuilder;

    @GetMapping("/market/show/sell/list")
    public Response listItemsToSell(final UserEntity userEntity) {
        return listItemsToSellResponseBuilder.build(
                ListItemsToSellResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .items(
                                inventoryItemTypeSorter.sortByType(inventoryEntityFactory.getEntity(userEntity.getId()).getItems())
                        )
                        .build()
        );
    }

    @GetMapping("/market/show/sell/{target}")
    public Response showItemToSell(final UserEntity userEntity, @PathVariable final int target) {
        final ItemDefinition itemDefinition = itemDefinitionCache.getDefinition(target);

        return showItemToSellResponseBuilder.build(
                ShowItemToSellResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .amount(
                                inventoryEntityFactory.getEntity(userEntity.getId()).getItemAmount(itemDefinition)
                        )
                        .item(itemDefinition)
                        .build()
        );
    }
}
