package com.morethanheroic.swords.market.view.service.domain.buy;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.market.domain.MarketOfferInformationEntry;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MarketInformationEntryListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final List<MarketOfferInformationEntry> offers;
    private final ItemType type;
}
