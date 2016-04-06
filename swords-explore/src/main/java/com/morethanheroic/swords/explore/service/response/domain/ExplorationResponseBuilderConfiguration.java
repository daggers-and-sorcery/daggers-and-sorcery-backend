package com.morethanheroic.swords.explore.service.response.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExplorationResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final ExplorationResult explorationEventEntryResults;
}
