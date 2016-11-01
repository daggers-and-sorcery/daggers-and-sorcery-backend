package com.morethanheroic.swords.market.view.controller.sell;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.view.response.service.domain.sell.ShowItemToSellResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.response.service.sell.ShowItemToSellResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShowItemToSellToMarketController {

    private final ShowItemToSellResponseBuilder showItemToSellResponseBuilder;
    private final ItemDefinitionCache itemDefinitionCache;
    private final InventoryEntityFactory inventoryEntityFactory;

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
