package com.morethanheroic.swords.skill.leatherworking.service.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingInfoResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LeatherworkingCuringInfoResponseBuilder implements ResponseBuilder<CookingInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final LeatherworkingCuringRecipeListPartialResponseBuilder leatherworkingCuringRecipeListPartialResponseBuilder;

    @Override
    public Response build(CookingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("recipes", leatherworkingCuringRecipeListPartialResponseBuilder.build(responseBuilderConfiguration));

        return response;
    }
}
