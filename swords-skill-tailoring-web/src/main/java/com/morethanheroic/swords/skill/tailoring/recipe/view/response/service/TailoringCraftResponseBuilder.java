package com.morethanheroic.swords.skill.tailoring.recipe.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.tailoring.recipe.view.response.service.domain.TailoringCraftResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TailoringCraftResponseBuilder implements ResponseBuilder<TailoringCraftResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final TailoringCraftPartialResponseBuilder tailoringCraftPartialResponseBuilder;

    @Override
    public Response build(TailoringCraftResponseBuilderConfiguration tailoringCraftResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(tailoringCraftResponseBuilderConfiguration.getUserEntity());

        response.setData("result", tailoringCraftPartialResponseBuilder.build(tailoringCraftResponseBuilderConfiguration));

        return response;
    }
}
