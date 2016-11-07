package com.morethanheroic.swords.market.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.market.view.response.service.domain.ListMyOffersResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListMyOffersResponseBuilder implements ResponseBuilder<ListMyOffersResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;

    @Override
    public Response build(ListMyOffersResponseBuilderConfiguration listMyOffersResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(listMyOffersResponseBuilderConfiguration.getUserEntity());

        response.setData("offers", );

        return response;
    }
}
