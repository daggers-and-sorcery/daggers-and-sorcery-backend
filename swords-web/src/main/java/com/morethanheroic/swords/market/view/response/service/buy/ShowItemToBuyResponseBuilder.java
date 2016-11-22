package com.morethanheroic.swords.market.view.response.service.buy;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.response.service.domain.buy.ListingPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.response.service.domain.buy.ShowItemToBuyResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowItemToBuyResponseBuilder implements ResponseBuilder<ShowItemToBuyResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;
    private final ListingPartialResponseBuilder listingPartialResponseBuilder;

    @Override
    public Response build(final ShowItemToBuyResponseBuilderConfiguration showItemToBuyResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(showItemToBuyResponseBuilderConfiguration.getUserEntity());

        response.setData("item", identifiedItemPartialResponseBuilder.build(
            IdentifiedItemPartialResponseBuilderConfiguration.builder()
                .item(showItemToBuyResponseBuilderConfiguration.getItem())
                .build()
        ));
        response.setData("listings", showItemToBuyResponseBuilderConfiguration.getListings().stream()
            .map((listing) ->
                listingPartialResponseBuilder.build(
                    ListingPartialResponseBuilderConfiguration.builder()
                    .listing(listing)
                    .build()
                )
            )
            .collect(
                Collectors.toList()
            )
        );

        return response;
    }
}
