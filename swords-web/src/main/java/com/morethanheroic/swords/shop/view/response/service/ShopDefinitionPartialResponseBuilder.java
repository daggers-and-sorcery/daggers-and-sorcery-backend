package com.morethanheroic.swords.shop.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.view.response.domain.configuration.ShopDefinitionPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.domain.ShopDefinitionPartialResponse;
import org.springframework.stereotype.Service;

@Service
public class ShopDefinitionPartialResponseBuilder implements PartialResponseBuilder<ShopDefinitionPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(ShopDefinitionPartialResponseBuilderConfiguration shopDefinitionPartialResponseBuilderConfiguration) {
        final ShopDefinition shopDefinition = shopDefinitionPartialResponseBuilderConfiguration.getShopDefinition();

        return ShopDefinitionPartialResponse.builder()
                .id(shopDefinition.getId())
                .name(shopDefinition.getName())
                .build();
    }
}
