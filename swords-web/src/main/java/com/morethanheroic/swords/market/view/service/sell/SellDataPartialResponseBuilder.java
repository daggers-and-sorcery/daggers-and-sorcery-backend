package com.morethanheroic.swords.market.view.service.sell;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.market.view.service.domain.sell.SellDataPartialResponse;
import com.morethanheroic.swords.market.view.service.domain.sell.ShowItemToSellResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class SellDataPartialResponseBuilder implements PartialResponseBuilder<ShowItemToSellResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(ShowItemToSellResponseBuilderConfiguration showItemToSellResponseBuilderConfiguration) {
        return SellDataPartialResponse.builder()
                .maximumAmount(showItemToSellResponseBuilderConfiguration.getAmount())
                .build();
    }
}
