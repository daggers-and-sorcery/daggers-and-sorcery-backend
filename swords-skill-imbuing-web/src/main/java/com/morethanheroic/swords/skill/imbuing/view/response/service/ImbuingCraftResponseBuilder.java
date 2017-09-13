package com.morethanheroic.swords.skill.imbuing.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.imbuing.view.response.service.domain.ImbuingCraftResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImbuingCraftResponseBuilder implements ResponseBuilder<ImbuingCraftResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ImbuingRecipeCraftResultPartialResponseBuilder imbuingRecipeCraftResultPartialResponseBuilder;

    @Override
    public Response build(ImbuingCraftResponseBuilderConfiguration imbuingCraftResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(imbuingCraftResponseBuilderConfiguration.getUserEntity());

        response.setData("result", imbuingRecipeCraftResultPartialResponseBuilder.build(imbuingCraftResponseBuilderConfiguration));

        return response;
    }
}
