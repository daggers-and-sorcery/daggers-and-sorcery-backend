package com.morethanheroic.swords.shop.view.response.service.sell;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.UnidentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.UnidentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.domain.sell.ShopSellItemPartialResponse;
import com.morethanheroic.swords.shop.view.response.domain.sell.configuration.ShopSellListItemPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopSellListItemPartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<ShopSellListItemPartialResponseBuilderConfiguration> {

    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;
    private final UnidentifiedItemPartialResponseBuilder unidentifiedItemPartialResponseBuilder;

    @Override
    public List<ShopSellItemPartialResponse> build(ShopSellListItemPartialResponseBuilderConfiguration shopSellListItemPartialResponseBuilderConfiguration) {
        final List<ShopSellItemPartialResponse> result = new ArrayList<>();

        for (ShopSellItem shopSellItem : shopSellListItemPartialResponseBuilderConfiguration.getShopItems()) {
            final InventoryItem inventoryItem = shopSellItem.getInventoryItem();
            final int sellPrice = shopSellItem.getSellPrice();

            if (sellPrice == 0) {
                continue;
            }

            if (inventoryItem.isIdentified()) {
                result.add(ShopSellItemPartialResponse.builder()
                        .amount(inventoryItem.getAmount())
                        .price(shopSellItem.getSellPrice())
                        .definition(
                                identifiedItemPartialResponseBuilder.build(
                                        IdentifiedItemPartialResponseBuilderConfiguration.builder()
                                                .item(inventoryItem.getItem())
                                                .build()
                                )
                        )
                        .build());
            } else {
                result.add(ShopSellItemPartialResponse.builder()
                        .amount(inventoryItem.getAmount())
                        .price(shopSellItem.getSellPrice())
                        .definition(
                                unidentifiedItemPartialResponseBuilder.build(
                                        UnidentifiedItemPartialResponseBuilderConfiguration.builder()
                                                .item(inventoryItem.getItem())
                                                .sessionEntity(shopSellListItemPartialResponseBuilderConfiguration.getSessionEntity())
                                                .build()
                                )
                        )
                        .build());
            }
        }

        return result;
    }
}
