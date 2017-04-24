package com.morethanheroic.swords.skill.herblore.view.response.service.recipe;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.recipe.HerbloreCraftResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HerbloreCraftResponseBuilder implements ResponseBuilder<HerbloreCraftResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final HerbloreCraftPartialResponseBuilder herbloreCraftPartialResponseBuilder;

    @Override
    public Response build(final HerbloreCraftResponseBuilderConfiguration herbloreCraftResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(herbloreCraftResponseBuilderConfiguration.getUserEntity());

        response.setData("result", herbloreCraftPartialResponseBuilder.build(herbloreCraftResponseBuilderConfiguration));

        return response;
    }
}
