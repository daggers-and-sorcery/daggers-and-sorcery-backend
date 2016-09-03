package com.morethanheroic.swords.skill.scavenging.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.scavenging.view.response.domain.ScavengingInfoResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScavengingInfoResponseBuilder implements ResponseBuilder<ScavengingInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ScavengingPointsPartialResponseBuilder scavengingPointsPartialResponseBuilder;

    @Override
    public Response build(ScavengingInfoResponseBuilderConfiguration scavengingInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(scavengingInfoResponseBuilderConfiguration.getUserEntity());

        response.setData("scavengingInfo", scavengingPointsPartialResponseBuilder.build(scavengingInfoResponseBuilderConfiguration));

        return response;
    }
}
