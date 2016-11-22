package com.morethanheroic.swords.skill.fletching.view.response.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FletchingInfoResponseBuilderConfigration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final int fletchingLevel;
}
