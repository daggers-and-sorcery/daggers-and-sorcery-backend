package com.morethanheroic.swords.market.view.response.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.market.domain.MarketEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ListMyOffersPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final MarketEntity marketEntity;
}
