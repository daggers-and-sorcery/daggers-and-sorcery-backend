package com.morethanheroic.swords.market.view.service.domain.buy;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.market.domain.MarketEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListingPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final MarketEntity listing;
}
