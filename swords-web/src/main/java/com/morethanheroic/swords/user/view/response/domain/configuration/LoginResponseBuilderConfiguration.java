package com.morethanheroic.swords.user.view.response.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.Builder;
import lombok.Getter;

/**
 * Contains the data that's required while building a response for the login request.
 */
@Getter
@Builder
public class LoginResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final boolean successful;
}
