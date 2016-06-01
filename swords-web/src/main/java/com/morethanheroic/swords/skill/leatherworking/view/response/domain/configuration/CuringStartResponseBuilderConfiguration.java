package com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.domain.CuringResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CuringStartResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final CuringResult curingResult;
}
