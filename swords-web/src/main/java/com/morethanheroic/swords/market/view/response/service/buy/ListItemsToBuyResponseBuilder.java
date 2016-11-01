package com.morethanheroic.swords.market.view.response.service.buy;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.market.view.response.service.domain.buy.ListItemsToBuyResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListItemsToBuyResponseBuilder implements ResponseBuilder<ListItemsToBuyResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ListedItemsPartialResponseCollectionBuilder listedItemsPartialResponseCollectionBuilder;

    @Override
    public Response build(ListItemsToBuyResponseBuilderConfiguration listItemsToBuyResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(listItemsToBuyResponseBuilderConfiguration.getUserEntity());

        response.setData("types", listedItemsPartialResponseCollectionBuilder.build(listItemsToBuyResponseBuilderConfiguration));

        return response;
    }
}
