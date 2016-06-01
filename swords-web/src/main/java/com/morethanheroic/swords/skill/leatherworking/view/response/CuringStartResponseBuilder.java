package com.morethanheroic.swords.skill.leatherworking.view.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.CuringStartResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuringStartResponseBuilder implements ResponseBuilder<CuringStartResponseBuilderConfiguration> {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private CuringResultPartialResponseBuilder curingResultPartialResponseBuilder;

    @Override
    public Response build(CuringStartResponseBuilderConfiguration curingStartResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(curingStartResponseBuilderConfiguration.getUserEntity());

        response.setData("result", curingResultPartialResponseBuilder.build(curingStartResponseBuilderConfiguration));

        return response;
    }
}
