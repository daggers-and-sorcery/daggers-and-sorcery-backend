package com.morethanheroic.swords.market.view.response.service.sell;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.market.view.response.service.domain.sell.SellItemPartialResponse;
import com.morethanheroic.swords.market.view.response.service.domain.sell.SellItemResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class SellItemPartialResponseBuilder implements PartialResponseBuilder<SellItemResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(final SellItemResponseBuilderConfiguration sellItemResponseBuilderConfiguration) {
        return SellItemPartialResponse.builder()
                .sellingResult(sellItemResponseBuilderConfiguration.getSellingResult())
                .build();
    }
}
