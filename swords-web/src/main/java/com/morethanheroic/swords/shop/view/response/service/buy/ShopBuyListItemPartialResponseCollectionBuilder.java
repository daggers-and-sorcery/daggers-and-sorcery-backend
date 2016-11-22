package com.morethanheroic.swords.shop.view.response.service.buy;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.domain.buy.ShopBuyItemPartialResponse;
import com.morethanheroic.swords.shop.view.response.domain.buy.configuration.ShopBuyListItemPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopBuyListItemPartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<ShopBuyListItemPartialResponseBuilderConfiguration> {

    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;

    @Override
    public List<ShopBuyItemPartialResponse> build(ShopBuyListItemPartialResponseBuilderConfiguration buyListItemPartialResponseBuilderConfiguration) {
        return buyListItemPartialResponseBuilderConfiguration.getShopItems()
                .stream()
                .map(
                        shopItem -> ShopBuyItemPartialResponse.builder()
                                .price(shopItem.getBuyPrice())
                                .amount(shopItem.getAmount())
                                .definition(identifiedItemPartialResponseBuilder.build(
                                        IdentifiedItemPartialResponseBuilderConfiguration.builder()
                                                .item(shopItem.getItem())
                                                .build()
                                ))
                                .build()
                )
                .collect(
                        Collectors.toList()
                );
    }
}
