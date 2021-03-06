package com.morethanheroic.swords.market.view.response.service.sell;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.response.service.domain.sell.ShowItemToSellResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowItemToSellResponseBuilder implements ResponseBuilder<ShowItemToSellResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;
    private final SellDataPartialResponseBuilder sellDataPartialResponseBuilder;

    @Override
    public Response build(final ShowItemToSellResponseBuilderConfiguration showItemToSellResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(showItemToSellResponseBuilderConfiguration.getUserEntity());

        response.setData("item", identifiedItemPartialResponseBuilder.build(
                IdentifiedItemPartialResponseBuilderConfiguration.builder()
                        .item(showItemToSellResponseBuilderConfiguration.getItem())
                        .build()
                )
        );
        response.setData("sellData", sellDataPartialResponseBuilder.build(showItemToSellResponseBuilderConfiguration));

        return response;
    }
}
