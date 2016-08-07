package com.morethanheroic.swords.shop.view.response.service.buy;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.shop.view.response.domain.buy.ShopBuyTypeListPartialResponse;
import com.morethanheroic.swords.shop.view.response.domain.buy.configuration.ShopBuyListItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.domain.buy.configuration.ShopBuyTypeListPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopBuyTypeListPartialResponseBuilder implements PartialResponseBuilder<ShopBuyTypeListPartialResponseBuilderConfiguration> {

    private final ShopBuyListItemPartialResponseCollectionBuilder shopBuyListItemPartialResponseCollectionBuilder;

    @Override
    public PartialResponse build(ShopBuyTypeListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return ShopBuyTypeListPartialResponse.builder()
                .typeName(responseBuilderConfiguration.getItemType().getName())
                .items(shopBuyListItemPartialResponseCollectionBuilder.build(
                        ShopBuyListItemPartialResponseBuilderConfiguration.builder()
                                .shopItems(responseBuilderConfiguration.getItems())
                                .build()
                        )
                )
                .build();
    }
}
