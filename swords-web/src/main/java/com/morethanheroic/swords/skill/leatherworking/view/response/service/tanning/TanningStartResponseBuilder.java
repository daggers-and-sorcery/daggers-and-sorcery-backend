package com.morethanheroic.swords.skill.leatherworking.view.response.service.tanning;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.tanning.TanningStartResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TanningStartResponseBuilder implements ResponseBuilder<TanningStartResponseBuilderConfiguration> {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private TanningResultPartialResponseBuilder tanningResultPartialResponseBuilder;

    @Override
    public Response build(TanningStartResponseBuilderConfiguration tanningStartResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(tanningStartResponseBuilderConfiguration.getUserEntity());

        response.setData("result", tanningResultPartialResponseBuilder.build(tanningStartResponseBuilderConfiguration));

        return response;
    }
}
