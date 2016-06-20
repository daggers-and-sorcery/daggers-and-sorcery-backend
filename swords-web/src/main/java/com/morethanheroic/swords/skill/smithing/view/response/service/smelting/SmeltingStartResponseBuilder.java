package com.morethanheroic.swords.skill.smithing.view.response.service.smelting;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.smelting.SmeltingStartResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmeltingStartResponseBuilder implements ResponseBuilder<SmeltingStartResponseBuilderConfiguration> {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private SmeltingResultPartialResponseBuilder smeltingResultPartialResponseBuilder;

    @Override
    public Response build(SmeltingStartResponseBuilderConfiguration smeltingStartResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(smeltingStartResponseBuilderConfiguration.getUserEntity());

        response.setData("result", smeltingResultPartialResponseBuilder.build(smeltingStartResponseBuilderConfiguration));

        return response;
    }
}