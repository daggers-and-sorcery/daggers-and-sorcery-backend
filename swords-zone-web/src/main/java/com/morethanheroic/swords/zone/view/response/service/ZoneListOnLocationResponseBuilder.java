package com.morethanheroic.swords.zone.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.zone.view.response.service.domain.ZoneListOnLocationResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneListOnLocationResponseBuilder implements ResponseBuilder<ZoneListOnLocationResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ZoneListOnLocationPartialResponseCollectionBuilder zoneListOnLocationPartialResponseCollectionBuilder;

    @Override
    public Response build(final ZoneListOnLocationResponseBuilderConfiguration zoneListOnLocationResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(zoneListOnLocationResponseBuilderConfiguration.getUserEntity());

        response.setData("zones", zoneListOnLocationPartialResponseCollectionBuilder.build(zoneListOnLocationResponseBuilderConfiguration));

        return response;
    }
}
