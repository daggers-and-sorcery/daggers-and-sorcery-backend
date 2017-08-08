package com.morethanheroic.swords.skill.view.response.domain.configuration;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SkillLevelPartialResponse extends PartialResponse {

    private int level;
}
