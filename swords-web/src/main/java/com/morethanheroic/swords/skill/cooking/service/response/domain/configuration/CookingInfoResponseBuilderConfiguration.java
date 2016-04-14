package com.morethanheroic.swords.skill.cooking.service.response.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingInfoResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private UserEntity userEntity;
}
