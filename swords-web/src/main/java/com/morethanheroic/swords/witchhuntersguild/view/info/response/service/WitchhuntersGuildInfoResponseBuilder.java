package com.morethanheroic.swords.witchhuntersguild.view.info.response.service;


import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.witchhuntersguild.view.info.response.service.domain.WitchhuntersGuildInfoResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WitchhuntersGuildInfoResponseBuilder implements ResponseBuilder<WitchhuntersGuildInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final WitchhuntersGuildInfoPartialResponseBuilder witchhuntersGuildInfoPartialResponseBuilder;

    @Override
    public Response build(WitchhuntersGuildInfoResponseBuilderConfiguration witchhuntersGuildInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(witchhuntersGuildInfoResponseBuilderConfiguration.getUserEntity());

        response.setData("info", witchhuntersGuildInfoPartialResponseBuilder.build(witchhuntersGuildInfoResponseBuilderConfiguration));

        return response;
    }
}
