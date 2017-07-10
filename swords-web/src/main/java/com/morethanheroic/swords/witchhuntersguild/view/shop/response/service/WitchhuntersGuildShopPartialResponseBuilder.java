package com.morethanheroic.swords.witchhuntersguild.view.shop.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.witchhuntersguild.view.shop.response.service.domain.WitchhuntersGuildShopPartialResponse;
import com.morethanheroic.swords.witchhuntersguild.view.shop.response.service.domain.WitchhuntersGuildShopResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class WitchhuntersGuildShopPartialResponseBuilder implements PartialResponseBuilder<WitchhuntersGuildShopResponseBuilderConfiguration> {

    private static final int NO_SHOP = -1;

    @Override
    public PartialResponse build(final WitchhuntersGuildShopResponseBuilderConfiguration witchhuntersGuildShopResponseBuilderConfiguration) {
        return WitchhuntersGuildShopPartialResponse.builder()
                .shopId(
                        witchhuntersGuildShopResponseBuilderConfiguration.getShopDefinition()
                                .map(ShopDefinition::getId)
                                .orElse(NO_SHOP)
                )
                .build();
    }
}
