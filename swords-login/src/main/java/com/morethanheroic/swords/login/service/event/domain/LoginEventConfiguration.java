package com.morethanheroic.swords.login.service.event.domain;

import com.morethanheroic.swords.event.EventConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * Configuration for LoginEvents.
 */
public class LoginEventConfiguration implements EventConfiguration {

    private final UserEntity userEntity;

    public LoginEventConfiguration(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}
