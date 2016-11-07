package com.morethanheroic.swords.market.view.response.service.sell;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.market.view.response.service.domain.sell.SellItemResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellItemResponseBuilder implements ResponseBuilder<SellItemResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final SellItemPartialResponseBuilder sellItemPartialResponseBuilder;

    @Override
    public Response build(SellItemResponseBuilderConfiguration sellItemResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(sellItemResponseBuilderConfiguration.getUserEntity());

        response.setData("result", sellItemPartialResponseBuilder.build(sellItemResponseBuilderConfiguration));

        return response;
    }
}
