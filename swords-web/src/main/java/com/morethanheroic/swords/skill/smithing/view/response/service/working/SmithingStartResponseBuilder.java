package com.morethanheroic.swords.skill.smithing.view.response.service.working;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.working.SmithingStartResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmithingStartResponseBuilder implements ResponseBuilder<SmithingStartResponseBuilderConfiguration> {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private SmithingResultPartialResponseBuilder smithingResultPartialResponseBuilder;

    @Override
    public Response build(SmithingStartResponseBuilderConfiguration smithingStartResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(smithingStartResponseBuilderConfiguration.getUserEntity());

        response.setData("result", smithingResultPartialResponseBuilder.build(smithingStartResponseBuilderConfiguration));

        return response;
    }
}
