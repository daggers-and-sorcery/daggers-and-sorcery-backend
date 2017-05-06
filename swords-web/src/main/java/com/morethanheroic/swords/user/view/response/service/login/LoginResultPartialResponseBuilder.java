package com.morethanheroic.swords.user.view.response.service.login;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.user.view.response.domain.LoginResultPartialResponse;
import com.morethanheroic.swords.user.view.response.domain.configuration.LoginResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

/**
 * Builds the result for a login request.
 */
@Service
public class LoginResultPartialResponseBuilder implements PartialResponseBuilder<LoginResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(final LoginResponseBuilderConfiguration loginResponseBuilderConfiguration) {
        return LoginResultPartialResponse.builder()
                .successful(loginResponseBuilderConfiguration.isSuccessful())
                .build();
    }
}
