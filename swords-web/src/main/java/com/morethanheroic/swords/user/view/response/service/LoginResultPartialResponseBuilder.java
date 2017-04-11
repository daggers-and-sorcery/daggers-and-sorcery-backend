package com.morethanheroic.swords.user.view.response.service;

import org.springframework.stereotype.Service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.user.view.response.domain.LoginResultPartialResponse;
import com.morethanheroic.swords.user.view.response.domain.configuration.LoginResponseBuilderConfiguration;

/**
 * Builds the result for a login request.
 */
@Service
public class LoginResultPartialResponseBuilder implements PartialResponseBuilder<LoginResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(final LoginResponseBuilderConfiguration loginResponseBuilderConfiguration) {
        return LoginResultPartialResponse.builder()
                                         .successful(loginResponseBuilderConfiguration.isSuccessful())
                                         .error(loginResponseBuilderConfiguration.getError())
                                         .showPrelude(loginResponseBuilderConfiguration.isShowPrelude())
            .build();
    }
}
