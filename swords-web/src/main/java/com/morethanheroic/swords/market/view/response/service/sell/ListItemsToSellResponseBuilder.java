package com.morethanheroic.swords.market.view.response.service.sell;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.market.view.response.service.domain.sell.ListItemsToSellResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.response.service.domain.sell.SellItemToMarketItemTypeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListItemsToSellResponseBuilder implements ResponseBuilder<ListItemsToSellResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final MarketEntityTypeInformationPartialResponseBuilder marketEntityTypeInformationPartialResponseBuilder;

    @Override
    public Response build(final ListItemsToSellResponseBuilderConfiguration listItemsToSellResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(listItemsToSellResponseBuilderConfiguration.getUserEntity());

        response.setData("items",
                listItemsToSellResponseBuilderConfiguration.getItems().entrySet()
                        .stream()
                        .map(
                                itemTypeListEntry ->
                                        marketEntityTypeInformationPartialResponseBuilder.build(
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
