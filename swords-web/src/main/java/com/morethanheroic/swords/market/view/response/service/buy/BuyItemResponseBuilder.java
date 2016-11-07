package com.morethanheroic.swords.market.view.response.service.buy;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.market.view.response.service.domain.buy.BuyItemResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyItemResponseBuilder implements ResponseBuilder<BuyItemResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final BuyItemPartialResponseBuilder buyItemPartialResponseBuilder;

    @Override
    public Response build(BuyItemResponseBuilderConfiguration buyItemResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(buyItemResponseBuilderConfiguration.getUserEntity());

        response.setData("result", buyItemPartialResponseBuilder.build(buyItemResponseBuilderConfiguration));

        return response;
    }
}
