package com.morethanheroic.swords.skill.leatherworking.service.response.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CuringInfoResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private UserEntity userEntity;
}