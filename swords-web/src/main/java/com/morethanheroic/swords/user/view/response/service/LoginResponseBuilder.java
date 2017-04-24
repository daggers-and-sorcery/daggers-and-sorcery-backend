package com.morethanheroic.swords.user.view.response.service;

import org.springframework.stereotype.Service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.view.response.domain.configuration.LoginResponseBuilderConfiguration;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginResponseBuilder implements ResponseBuilder<LoginResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final LoginResultPartialResponseBuilder loginResultPartialResponseBuilder;

    @Override
    public Response build(final LoginResponseBuilderConfiguration loginResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(loginResponseBuilderConfiguration.getUserEntity());

        response.setData("result", loginResultPartialResponseBuilder.build(loginResponseBuilderConfiguration));

        return response;
    }
}
