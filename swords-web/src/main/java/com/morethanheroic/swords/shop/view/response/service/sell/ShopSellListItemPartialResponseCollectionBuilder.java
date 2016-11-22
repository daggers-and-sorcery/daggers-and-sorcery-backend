package com.morethanheroic.swords.shop.view.response.service.sell;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.item.view.response.service.domain.ItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.ItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.domain.sell.ShopSellItemPartialResponse;
import com.morethanheroic.swords.shop.view.response.domain.sell.configuration.ShopSellListItemPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopSellListItemPartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<ShopSellListItemPartialResponseBuilderConfiguration> {

    private final ItemPartialResponseBuilder itemPartialResponseBuilder;

    @Override
    public List<ShopSellItemPartialResponse> build(ShopSellListItemPartialResponseBuilderConfiguration shopSellListItemPartialResponseBuilderConfiguration) {
        return shopSellListItemPartialResponseBuilderConfiguration.getShopItems()
                .stream()
                .filter(
                        (shopSellItem) -> shopSellItem.getSellPrice() > 0
                )
                .map(
                        (shopSellItem) -> {
                            final InventoryItem inventoryItem = shopSellItem.getInventoryItem();

                            return ShopSellItemPartialResponse.builder()
                                    .amount(inventoryItem.getAmount())
                                    .price(shopSellItem.getSellPrice())
                                    .definition(
                                            itemPartialResponseBuilder.build(
                                                    ItemPartialResponseBuilderConfiguration.builder()
                                                            .item(inventoryItem.getItem())
                                                            .isIdentified(inventoryItem.isIdentified())
                                                            .sessionEntity(shopSellListItemPartialResponseBuilderConfiguration.getSessionEntity())
                                                            .build()
                                            )
                                    )
                                    .build();
                        }
                )
                .collect(
                        Collectors.toList()
                );
    }
}
