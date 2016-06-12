package com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.working;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.domain.LeatherworkingResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WorkingStartResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final LeatherworkingResult leatherworkingResult;
}
