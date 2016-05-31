package com.morethanheroic.swords.skill.leatherworking.view.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.CuringInfoResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CuringInfoResponseBuilder implements ResponseBuilder<CuringInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final CuringRecipeListPartialResponseBuilder curingRecipeListPartialResponseBuilder;
    private final CuringListPartialResponseBuilder curingListPartialResponseBuilder;

    @Override
    public Response build(CuringInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        //TODO: add the level too
        response.setData("recipes", curingRecipeListPartialResponseBuilder.build(responseBuilderConfiguration));
        response.setData("curing_list", curingListPartialResponseBuilder.build(responseBuilderConfiguration));

        return response;
    }
}
