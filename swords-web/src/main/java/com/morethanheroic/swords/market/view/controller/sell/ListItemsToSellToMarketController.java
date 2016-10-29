package com.morethanheroic.swords.market.view.controller.sell;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.service.sorter.InventoryItemTypeSorter;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.view.service.domain.sell.ListItemsToSellResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.service.sell.ListItemsToSellResponseBuilder;
import com.morethanheroic.swords.market.view.service.sell.ShowItemToSellResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ListItemsToSellToMarketController {

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
}
