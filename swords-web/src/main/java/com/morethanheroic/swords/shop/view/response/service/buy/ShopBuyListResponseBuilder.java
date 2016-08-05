package com.morethanheroic.swords.shop.view.response.service.buy;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.shop.view.response.domain.ShopDefinitionPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.domain.buy.configuration.ShopBuyListResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.domain.buy.configuration.ShopBuyTypeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.service.ShopDefinitionPartialResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopBuyListResponseBuilder implements ResponseBuilder<ShopBuyListResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ShopBuyListItemPartialResponseBuilder shopBuyListItemPartialResponseBuilder;
    private final ShopDefinitionPartialResponseBuilder shopDefinitionPartialResponseBuilder;
    private final ShopBuyTypeListPartialResponseBuilder shopBuyTypeListPartialResponseBuilder;

    @Override
    public Response build(ShopBuyListResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("items",
                responseBuilderConfiguration.getItems().entrySet()
                        .stream()
                        .map(itemTypeListEntry ->
                                shopBuyTypeListPartialResponseBuilder.build(
                                        ShopBuyTypeListPartialResponseBuilderConfiguration.builder()
                                                .itemType(itemTypeListEntry.getKey())
                                                .items(itemTypeListEntry.getValue())
                                                .build()
                                )
                        ).collect(Collectors.toList())
        );

        response.setData("definition", shopDefinitionPartialResponseBuilder.build(
                ShopDefinitionPartialResponseBuilderConfiguration.builder()
                        .shopDefinition(responseBuilderConfiguration.getShopDefinition())
                        .build()
                )
        );

        return response;
    }
}
