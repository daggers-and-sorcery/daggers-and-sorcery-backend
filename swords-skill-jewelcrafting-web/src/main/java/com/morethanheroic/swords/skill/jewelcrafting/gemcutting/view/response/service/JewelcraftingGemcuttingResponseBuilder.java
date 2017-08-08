package com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service.domain.JewelcraftingGemcuttingResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JewelcraftingGemcuttingResponseBuilder implements ResponseBuilder<JewelcraftingGemcuttingResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final JewelcraftingGemcuttingPartialResponseBuilder jewelcraftingGemcuttingPartialResponseBuilder;

    @Override
    public Response build(final JewelcraftingGemcuttingResponseBuilderConfiguration jewelcraftingGemcuttingResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(jewelcraftingGemcuttingResponseBuilderConfiguration.getUserEntity());

        response.setData("result", jewelcraftingGemcuttingPartialResponseBuilder.build(jewelcraftingGemcuttingResponseBuilderConfiguration));

        return response;
    }
}
