package com.morethanheroic.swords.skill.crafting.view.response.service.recipe;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.crafting.view.response.service.recipe.domain.CraftingRecipeCraftResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CraftingRecipeCraftResponseBuilder implements ResponseBuilder<CraftingRecipeCraftResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final CraftingRecipeCraftResultPartialResponseBuilder craftingRecipeCraftResultPartialResponseBuilder;

    @Override
    public Response build(final CraftingRecipeCraftResponseBuilderConfiguration craftingRecipeCraftResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(craftingRecipeCraftResponseBuilderConfiguration.getUserEntity());

        response.setData("result", craftingRecipeCraftResultPartialResponseBuilder.build(craftingRecipeCraftResponseBuilderConfiguration));

        return response;
    }
}
