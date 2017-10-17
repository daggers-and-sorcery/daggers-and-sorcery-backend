package com.morethanheroic.swords.skill.tailoring.weaving.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.tailoring.weaving.view.response.service.domain.TailoringWeavingResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TailoringWeavingResponseBuilder implements ResponseBuilder<TailoringWeavingResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final TailoringWeavingPartialResponseBuilder tailoringWeavingPartialResponseBuilder;

    @Override
    public Response build(TailoringWeavingResponseBuilderConfiguration tailoringWeavingResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(tailoringWeavingResponseBuilderConfiguration.getUserEntity());

        response.setData("result", tailoringWeavingPartialResponseBuilder.build(tailoringWeavingResponseBuilderConfiguration));

        return response;
    }
}
