package com.morethanheroic.swords.skill.view.response.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SkillLevelPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final int skillLevel;
}
