package com.morethanheroic.swords.skill.herblore.view.response.service.cleaning;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.cleaning.HerbloreCleaningResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A response builder for the clean herb endpoint.
 */
@Service
@RequiredArgsConstructor
public class HerbloreCleaningResponseBuilder implements ResponseBuilder<HerbloreCleaningResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final HerbloreCleaningPartialResponseBuilder herbloreCleaningPartialResponseBuilder;

    @Override
    public Response build(HerbloreCleaningResponseBuilderConfiguration herbloreCleaningResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(herbloreCleaningResponseBuilderConfiguration.getUserEntity());

        response.setData("result", herbloreCleaningPartialResponseBuilder.build(herbloreCleaningResponseBuilderConfiguration));

        return response;
    }
}
