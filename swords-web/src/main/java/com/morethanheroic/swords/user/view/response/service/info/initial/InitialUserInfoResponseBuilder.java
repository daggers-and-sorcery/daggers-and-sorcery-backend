package com.morethanheroic.swords.user.view.response.service.info.initial;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.view.response.service.info.initial.domain.configuration.InitialUserInfoResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitialUserInfoResponseBuilder implements ResponseBuilder<InitialUserInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final WitchhuntersGuildInitialUserInfoPartialResponseBuilder witchhuntersGuildInitialUserInfoPartialResponseBuilder;

    @Override
    public Response build(final InitialUserInfoResponseBuilderConfiguration initialUserInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(initialUserInfoResponseBuilderConfiguration.getUserEntity());

        //TODO: Remove
        response.setData("loggedIn", initialUserInfoResponseBuilderConfiguration.getUserEntity() != null);
        response.setData("witchuntersGuildInfo", witchhuntersGuildInitialUserInfoPartialResponseBuilder.build(initialUserInfoResponseBuilderConfiguration));

        return response;
    }
}
