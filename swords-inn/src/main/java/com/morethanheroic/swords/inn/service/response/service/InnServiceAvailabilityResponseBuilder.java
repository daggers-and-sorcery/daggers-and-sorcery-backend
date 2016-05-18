package com.morethanheroic.swords.inn.service.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.inn.service.response.service.domain.InnServiceAvailabilityResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InnServiceAvailabilityResponseBuilder implements ResponseBuilder<InnServiceAvailabilityResponseBuilderConfiguration> {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private InnServicesPartialResponseBuilder innServicesPartialResponseBuilder;

    @Override
    public Response build(InnServiceAvailabilityResponseBuilderConfiguration innServiceAvailabilityResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(innServiceAvailabilityResponseBuilderConfiguration.getUserEntity());

        response.setData("services", innServicesPartialResponseBuilder.build(innServiceAvailabilityResponseBuilderConfiguration));

        return response;
    }
}
