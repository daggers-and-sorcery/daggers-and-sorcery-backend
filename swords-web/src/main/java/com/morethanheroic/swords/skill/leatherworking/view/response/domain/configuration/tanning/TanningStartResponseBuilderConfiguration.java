package com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.tanning;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.domain.TanningResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TanningStartResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final TanningResult tanningResult;
}
