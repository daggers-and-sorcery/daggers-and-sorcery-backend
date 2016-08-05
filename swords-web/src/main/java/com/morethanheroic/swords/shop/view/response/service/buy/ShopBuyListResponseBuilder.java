package com.morethanheroic.swords.shop.view.response.service.buy;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.shop.view.response.domain.ShopDefinitionPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.domain.buy.configuration.ShopBuyListItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.domain.buy.configuration.ShopBuyListResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.service.ShopDefinitionPartialResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopBuyListResponseBuilder implements ResponseBuilder<ShopBuyListResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ShopBuyListItemPartialResponseBuilder shopBuyListItemPartialResponseBuilder;
    private final ShopDefinitionPartialResponseBuilder shopDefinitionPartialResponseBuilder;

    @Override
    public Response build(ShopBuyListResponseBuilderConfiguration shopBuyListResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(shopBuyListResponseBuilderConfiguration.getUserEntity());

        response.setData("items", shopBuyListItemPartialResponseBuilder.build(
                ShopBuyListItemPartialResponseBuilderConfiguration.builder()
                        .shopItems(shopBuyListResponseBuilderConfiguration.getItems())
                        .build()
        ));

        response.setData("definition", shopDefinitionPartialResponseBuilder.build(
                ShopDefinitionPartialResponseBuilderConfiguration.builder()
                        .shopDefinition(shopBuyListResponseBuilderConfiguration.getShopDefinition())
                        .build()
                )
        );

        return response;
    }
}
