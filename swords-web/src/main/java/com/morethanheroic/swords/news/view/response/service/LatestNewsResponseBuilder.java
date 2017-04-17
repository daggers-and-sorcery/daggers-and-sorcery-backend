package com.morethanheroic.swords.news.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.news.view.response.service.domain.LatestNewsResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Builds responses for the latest news request.
 */
@Service
@RequiredArgsConstructor
public class LatestNewsResponseBuilder implements ResponseBuilder<LatestNewsResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final LatestNewsPartialResponseCollectionBuilder latestNewsPartialResponseCollectionBuilder;

    @Override
    public Response build(LatestNewsResponseBuilderConfiguration latestNewsResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(latestNewsResponseBuilderConfiguration.getUserEntity());

        response.setData("news", latestNewsPartialResponseCollectionBuilder.build(latestNewsResponseBuilderConfiguration));

        return response;
    }
}
