package com.morethanheroic.swords.market.view.response.service.buy;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.market.view.response.service.domain.buy.ListingPartialResponse;
import com.morethanheroic.swords.market.view.response.service.domain.buy.ListingPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ListingPartialResponseBuilder implements PartialResponseBuilder<ListingPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(final ListingPartialResponseBuilderConfiguration listingPartialResponseBuilderConfiguration) {
        final MarketEntity listing = listingPartialResponseBuilderConfiguration.getListing();

        return ListingPartialResponse.builder()
                .id(listing.getId())
                .seller(listing.getSeller().getUsername())
                .amount(listing.getAmount())
                .price(listing.getPrice())
                .build();
    }
}
