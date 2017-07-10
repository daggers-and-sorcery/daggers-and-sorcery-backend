package com.morethanheroic.swords.witchhuntersguild.view.shop.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.witchhuntersguild.view.shop.response.service.domain.WitchhuntersGuildShopResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A {@link ResponseBuilder} implementation for the Witchhunter's guild shop page.
 */
@Service
@RequiredArgsConstructor
public class WitchhuntersGuildShopResponseBuilder implements ResponseBuilder<WitchhuntersGuildShopResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final WitchhuntersGuildShopPartialResponseBuilder witchhuntersGuildShopPartialResponseBuilder;

    @Override
    public Response build(final WitchhuntersGuildShopResponseBuilderConfiguration witchhuntersGuildShopResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(witchhuntersGuildShopResponseBuilderConfiguration.getUserEntity());

        response.setData("shop", witchhuntersGuildShopPartialResponseBuilder.build(witchhuntersGuildShopResponseBuilderConfiguration));

        return response;
    }
}
