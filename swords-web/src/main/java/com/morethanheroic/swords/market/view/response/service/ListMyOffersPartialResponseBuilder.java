package com.morethanheroic.swords.market.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.market.view.response.service.domain.ListMyOffersEntryPartialResponse;
import com.morethanheroic.swords.market.view.response.service.domain.ListMyOffersPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListMyOffersPartialResponseBuilder implements PartialResponseBuilder<ListMyOffersPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(ListMyOffersPartialResponseBuilderConfiguration listMyOffersPartialResponseBuilderConfiguration) {
        final MarketEntity marketEntity = listMyOffersPartialResponseBuilderConfiguration.getMarketEntity();

        return ListMyOffersEntryPartialResponse.builder()
                .id(marketEntity.getId())
                .name(marketEntity.getItem().getName())
                .amount(marketEntity.getAmount())
                .price(marketEntity.getPrice())
                .build();
    }
}
