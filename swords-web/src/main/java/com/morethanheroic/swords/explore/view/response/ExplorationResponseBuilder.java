package com.morethanheroic.swords.explore.view.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.explore.view.response.domain.ExplorationResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExplorationResponseBuilder implements ResponseBuilder<ExplorationResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ExplorationEventInfoPartialResponseBuilder explorationExplorationEventInfoPartialResponseBuilder;
    private final ExplorationEventsPartialResponseBuilder explorationEventsPartialResponseBuilder;

    @Override
    public Response build(ExplorationResponseBuilderConfiguration explorationResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(explorationResponseBuilderConfiguration.getUserEntity());

        response.setData("info", explorationExplorationEventInfoPartialResponseBuilder.build(explorationResponseBuilderConfiguration));
        response.setData("events", explorationEventsPartialResponseBuilder.build(explorationResponseBuilderConfiguration));

        return response;
    }
}
