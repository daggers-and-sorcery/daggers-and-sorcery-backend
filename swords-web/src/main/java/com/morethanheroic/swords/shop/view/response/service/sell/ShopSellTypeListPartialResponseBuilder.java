package com.morethanheroic.swords.shop.view.response.service.sell;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.shop.view.response.domain.sell.ShopSellListItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.domain.sell.ShopSellTypeListPartialResponse;
import com.morethanheroic.swords.shop.view.response.domain.sell.ShopSellTypeListPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopSellTypeListPartialResponseBuilder implements PartialResponseBuilder<ShopSellTypeListPartialResponseBuilderConfiguration> {

    private final ShopSellListItemPartialResponseCollectionBuilder shopSellListItemPartialResponseBuilder;

    @Override
    public PartialResponse build(ShopSellTypeListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return ShopSellTypeListPartialResponse.builder()
                .typeName(responseBuilderConfiguration.getItemType().getName())
                .items(shopSellListItemPartialResponseBuilder.build(
                        ShopSellListItemPartialResponseBuilderConfiguration.builder()
                                .shopItems(responseBuilderConfiguration.getItems())
                                .build()
                        )
                )
                .build();
    }
}
