package com.morethanheroic.swords.skill.herblore.view.response.service.gathering;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering.GatheringStartResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GatheringStartResponseBuilder implements ResponseBuilder<GatheringStartResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final GatheringStartPartialResponseBuilder gatheringStartPartialResponseBuilder;

    @Override
    public Response build(GatheringStartResponseBuilderConfiguration gatheringStartResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(gatheringStartResponseBuilderConfiguration.getUserEntity());

        response.setData("result", gatheringStartPartialResponseBuilder.build(gatheringStartResponseBuilderConfiguration));

        return response;
    }
}
