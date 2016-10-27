package com.morethanheroic.swords.market.view.service.buy;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.market.view.service.domain.buy.ListingPartialResponse;
import com.morethanheroic.swords.market.view.service.domain.buy.ListingPartialResponseBuilderConfiguration;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListingPartialResponseBuilder implements PartialResponseBuilder<ListingPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(final ListingPartialResponseBuilderConfiguration listingPartialResponseBuilderConfiguration) {
        final MarketEntity listing = listingPartialResponseBuilderConfiguration.getListing();

        return ListingPartialResponse.builder()
            .seller(listing.getSeller().getUsername())
            .price(listing.getPrice())
            .build();
    }
}
