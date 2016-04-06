package com.morethanheroic.swords.explore.service.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.explore.service.response.domain.ExplorationResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExplorationResponseBuilder implements ResponseBuilder<ExplorationResponseBuilderConfiguration> {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ExplorationEventsPartialResponseBuilder explorationEventsPartialResponseBuilder;

    @Override
    public Response build(ExplorationResponseBuilderConfiguration explorationResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(explorationResponseBuilderConfiguration.getUserEntity());

        response.setData("events", explorationEventsPartialResponseBuilder.build(explorationResponseBuilderConfiguration));

        return response;
    }
}
