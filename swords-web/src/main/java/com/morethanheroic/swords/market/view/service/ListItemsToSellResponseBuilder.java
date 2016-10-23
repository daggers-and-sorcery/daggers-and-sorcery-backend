package com.morethanheroic.swords.market.view.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.market.view.service.domain.ListItemsToSellResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.service.domain.SellItemToMarketItemTypeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListItemsToSellResponseBuilder implements ResponseBuilder<ListItemsToSellResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final SellItemToMarketItemTypeListPartialResponseBuilder sellItemToMarketItemTypeListPartialResponseBuilder;

    @Override
    public Response build(final ListItemsToSellResponseBuilderConfiguration listItemsToSellResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(listItemsToSellResponseBuilderConfiguration.getUserEntity());

        response.setData("items",
                listItemsToSellResponseBuilderConfiguration.getItems().entrySet()
                        .stream()
                        .map(
                                itemTypeListEntry ->
                                        sellItemToMarketItemTypeListPartialResponseBuilder.build(
                                                SellItemToMarketItemTypeListPartialResponseBuilderConfiguration.builder()
                                                        .itemType(itemTypeListEntry.getKey().getName())
                                                        .items(itemTypeListEntry.getValue())
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
