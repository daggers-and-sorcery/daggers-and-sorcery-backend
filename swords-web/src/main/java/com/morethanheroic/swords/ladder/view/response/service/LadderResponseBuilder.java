package com.morethanheroic.swords.ladder.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.ladder.view.response.domain.configuration.LadderResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LadderResponseBuilder implements ResponseBuilder<LadderResponseBuilderConfiguration> {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private LadderInfoPartialResponseBuilder ladderInfoPartialResponseBuilder;

    @Override
    public Response build(LadderResponseBuilderConfiguration ladderResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(ladderResponseBuilderConfiguration.getUserEntity());

        response.setData("ladder_info", ladderInfoPartialResponseBuilder.build(ladderResponseBuilderConfiguration));

        return response;
    }
}
