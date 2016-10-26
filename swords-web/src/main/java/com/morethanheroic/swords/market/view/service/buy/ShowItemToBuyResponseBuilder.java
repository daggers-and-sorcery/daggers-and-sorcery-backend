package com.morethanheroic.swords.market.view.service.buy;

import org.springframework.stereotype.Service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.market.view.service.domain.buy.ShowItemToBuyResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowItemToBuyResponseBuilder implements ResponseBuilder<ShowItemToBuyResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;

    @Override
    public Response build(final ShowItemToBuyResponseBuilderConfiguration showItemToBuyResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(showItemToBuyResponseBuilderConfiguration.getUserEntity());

        //TODO

        return response;
    }
}
