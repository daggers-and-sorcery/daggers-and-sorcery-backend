package com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.service.gathering.domain.GatheringResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GatheringStartResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final GatheringResult result;
}
