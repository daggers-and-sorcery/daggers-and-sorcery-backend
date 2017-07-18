package com.morethanheroic.swords.witchhuntersguild.view.info.response.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WitchhuntersGuildInfoResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final boolean accessible;
}
