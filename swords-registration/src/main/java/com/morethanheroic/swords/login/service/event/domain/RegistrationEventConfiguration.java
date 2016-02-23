package com.morethanheroic.swords.login.service.event.domain;

import com.morethanheroic.event.EventConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * Configuration for RegistrationEvents.
 */
public class RegistrationEventConfiguration implements EventConfiguration {

    private final UserEntity userEntity;

    public RegistrationEventConfiguration(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}
