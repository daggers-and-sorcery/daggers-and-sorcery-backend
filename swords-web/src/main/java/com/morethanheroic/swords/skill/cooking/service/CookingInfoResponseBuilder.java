package com.morethanheroic.swords.skill.cooking.service;

import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.cooking.service.domain.configuration.CookingInfoResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingInfoResponseBuilder implements ResponseBuilder<CookingInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final CookingRecipeListPartialResponseBuilder cookingRecipeListPartialResponseBuilder;

    @Override
    public Response build(CookingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("recipes", cookingRecipeListPartialResponseBuilder.build(responseBuilderConfiguration));

        return response;
    }
}
