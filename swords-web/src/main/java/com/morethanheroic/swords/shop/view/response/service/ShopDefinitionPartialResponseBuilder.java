package com.morethanheroic.swords.shop.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.view.response.domain.ShopDefinitionPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.domain.buy.ShopDefinitionPartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShopDefinitionPartialResponseBuilder implements PartialResponseBuilder<ShopDefinitionPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(ShopDefinitionPartialResponseBuilderConfiguration shopDefinitionPartialResponseBuilderConfiguration) {
        final ShopDefinition shopDefinition = shopDefinitionPartialResponseBuilderConfiguration.getShopDefinition();

        return ShopDefinitionPartialResponse.builder()
                .name(shopDefinition.getName())
                .build();
    }
}
