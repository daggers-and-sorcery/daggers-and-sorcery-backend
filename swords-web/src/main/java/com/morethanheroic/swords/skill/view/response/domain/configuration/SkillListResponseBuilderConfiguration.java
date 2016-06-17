package com.morethanheroic.swords.skill.view.response.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SkillListResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
}
