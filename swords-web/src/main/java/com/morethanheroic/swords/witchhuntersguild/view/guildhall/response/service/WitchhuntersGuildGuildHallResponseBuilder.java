package com.morethanheroic.swords.witchhuntersguild.view.guildhall.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.witchhuntersguild.view.guildhall.response.service.domain.WitchhuntersGuildGuildHallResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A {@link ResponseBuilder} implementation for the Witchhunter's guild page.
 */
@Service
@RequiredArgsConstructor
public class WitchhuntersGuildGuildHallResponseBuilder implements ResponseBuilder<WitchhuntersGuildGuildHallResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final WitchhuntersGuildGuildHallPartialResponseBuilder witchhuntersGuildGuildHallPartialResponseBuilder;

    @Override
    public Response build(WitchhuntersGuildGuildHallResponseBuilderConfiguration witchhuntersGuildGuildHallResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(witchhuntersGuildGuildHallResponseBuilderConfiguration.getUserEntity());

        response.setData("guildhall", witchhuntersGuildGuildHallPartialResponseBuilder.build(witchhuntersGuildGuildHallResponseBuilderConfiguration));

        return response;
    }
}
