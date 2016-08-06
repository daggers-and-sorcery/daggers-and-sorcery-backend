package com.morethanheroic.swords.shop.view.response.service.buy;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.ItemModifierPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.ItemRequirementPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.domain.ShopItem;
import com.morethanheroic.swords.shop.view.response.domain.buy.ShopBuyItemPartialResponse;
import com.morethanheroic.swords.shop.view.response.domain.buy.configuration.ShopBuyListItemPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopBuyListItemPartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<ShopBuyListItemPartialResponseBuilderConfiguration> {

    private final ItemModifierPartialResponseBuilder itemModifierPartialResponseBuilder;
    private final ItemRequirementPartialResponseBuilder itemRequirementPartialResponseBuilder;
    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;

    @Override
    public List<ShopBuyItemPartialResponse> build(ShopBuyListItemPartialResponseBuilderConfiguration buyListItemPartialResponseBuilderConfiguration) {
        final List<ShopBuyItemPartialResponse> result = new ArrayList<>();

        for (ShopItem shopItem : buyListItemPartialResponseBuilderConfiguration.getShopItems()) {
            result.add(
                    ShopBuyItemPartialResponse.builder()
                            .price(shopItem.getBuyPrice())
                            .amount(shopItem.getAmount())
                            .item(identifiedItemPartialResponseBuilder.build(
                                    IdentifiedItemPartialResponseBuilderConfiguration.builder()
                                            .item(shopItem.getItem())
                                            .build()
                            ))
                            .build()
            );
        }

        return result;
    }
}
