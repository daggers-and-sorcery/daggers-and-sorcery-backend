package com.morethanheroic.swords.market.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.market.view.response.service.domain.ListMyOffersPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.response.service.domain.ListMyOffersResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListMyOffersResponseBuilder implements ResponseBuilder<ListMyOffersResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ListMyOffersPartialResponseBuilder listMyOffersPartialResponseBuilder;

    @Override
    public Response build(ListMyOffersResponseBuilderConfiguration listMyOffersResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(listMyOffersResponseBuilderConfiguration.getUserEntity());

        response.setData("offers", listMyOffersResponseBuilderConfiguration.getOffers().stream()
                .map(
                        (marketEntity -> listMyOffersPartialResponseBuilder.build(
                                ListMyOffersPartialResponseBuilderConfiguration.builder()
                                        .marketEntity(marketEntity)
                                        .build()
                        ))
                )
                .collect(
                        Collectors.toList()
                )
        );

        return response;
    }
}
