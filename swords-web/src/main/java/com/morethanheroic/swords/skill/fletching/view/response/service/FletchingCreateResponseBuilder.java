package com.morethanheroic.swords.skill.fletching.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.fletching.view.response.domain.FletchingCreateResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FletchingCreateResponseBuilder implements ResponseBuilder<FletchingCreateResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final FletchingCreatePartialResponseBuilder fletchingCreatePartialResponseBuilder;

    @Override
    public Response build(FletchingCreateResponseBuilderConfiguration fletchingCreateResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(fletchingCreateResponseBuilderConfiguration.getUserEntity());

        response.setData("result", fletchingCreatePartialResponseBuilder.build(fletchingCreateResponseBuilderConfiguration));

        return response;
    }
}
