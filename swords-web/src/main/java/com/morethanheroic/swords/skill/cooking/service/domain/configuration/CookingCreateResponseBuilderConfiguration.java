package com.morethanheroic.swords.skill.cooking.service.domain.configuration;

import com.morethanheroic.swords.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingCreateResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private UserEntity userEntity;
    private boolean success;
}
