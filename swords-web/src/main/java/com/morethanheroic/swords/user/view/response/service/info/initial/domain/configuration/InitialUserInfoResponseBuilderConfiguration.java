package com.morethanheroic.swords.user.view.response.service.info.initial.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InitialUserInfoResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final boolean witchhuntersGuildUnlocked;
}
