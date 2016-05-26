package com.morethanheroic.swords.skill.leatherworking.service.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.leatherworking.service.response.domain.configuration.CuringInfoResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CuringInfoResponseBuilder implements ResponseBuilder<CuringInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final CuringRecipeListPartialResponseBuilder curingRecipeListPartialResponseBuilder;

    @Override
    public Response build(CuringInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("recipes", curingRecipeListPartialResponseBuilder.build(responseBuilderConfiguration));

        return response;
    }
}
