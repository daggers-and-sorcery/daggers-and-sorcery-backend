package com.morethanheroic.swords.view.response.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.view.response.order.domain.InnServiceOrderResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;

@Service
public class TavernServiceOrderResponseBuilder implements ResponseBuilder<InnServiceOrderResponseBuilderConfiguration> {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private InnServiceOrderResultPartialResponseBuilder innServiceOrderResultPartialResponseBuilder;

    @Override
    public Response build(InnServiceOrderResponseBuilderConfiguration explorationResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(explorationResponseBuilderConfiguration.getUserEntity());

        response.setData("result", innServiceOrderResultPartialResponseBuilder.build(explorationResponseBuilderConfiguration));

        return response;
    }
}
