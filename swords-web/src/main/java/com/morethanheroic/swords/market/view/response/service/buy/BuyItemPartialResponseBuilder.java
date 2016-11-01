package com.morethanheroic.swords.market.view.response.service.buy;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.market.view.response.service.domain.buy.BuyItemPartialResponse;
import com.morethanheroic.swords.market.view.response.service.domain.buy.BuyItemResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class BuyItemPartialResponseBuilder implements PartialResponseBuilder<BuyItemResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(BuyItemResponseBuilderConfiguration buyItemResponseBuilderConfiguration) {
        return BuyItemPartialResponse.builder()
                .result(buyItemResponseBuilderConfiguration.getBuyingResult())
                .build();
    }
}
