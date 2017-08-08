package com.morethanheroic.swords.skill.jewelcrafting.recipe.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.jewelcrafting.recipe.view.response.service.domain.JewelcraftingCraftResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JewelcraftingCraftResponseBuilder implements ResponseBuilder<JewelcraftingCraftResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final JewelcraftingCraftPartialResponseBuilder jewelcraftingCraftPartialResponseBuilder;

    @Override
    public Response build(final JewelcraftingCraftResponseBuilderConfiguration jewelcraftingCraftResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(jewelcraftingCraftResponseBuilderConfiguration.getUserEntity());

        response.setData("result", jewelcraftingCraftPartialResponseBuilder.build(jewelcraftingCraftResponseBuilderConfiguration));

        return response;
    }
}
