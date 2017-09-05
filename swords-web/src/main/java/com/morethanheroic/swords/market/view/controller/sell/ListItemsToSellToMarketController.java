package com.morethanheroic.swords.market.view.controller.sell;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.service.sorter.InventoryItemTypeSorter;
import com.morethanheroic.swords.market.view.response.service.domain.sell.ListItemsToSellResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.response.service.sell.ListItemsToSellResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller that contains the logic for showing the items that th player can sell to the market.
 */
@RestController
@RequiredArgsConstructor
public class ListItemsToSellToMarketController {

    private final InventoryItemTypeSorter inventoryItemTypeSorter;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ListItemsToSellResponseBuilder listItemsToSellResponseBuilder;

    @GetMapping("/market/show/sell/list")
    public Response listItemsToSell(final UserEntity userEntity) {
        return listItemsToSellResponseBuilder.build(
                ListItemsToSellResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .items(
                                inventoryItemTypeSorter.sortByType(inventoryEntityFactory.getEntity(userEntity).getItems())
                        )
                        .build()
        );
    }
}
