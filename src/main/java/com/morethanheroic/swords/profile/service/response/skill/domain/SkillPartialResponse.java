package com.morethanheroic.swords.profile.service.response.skill.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SkillPartialResponse extends PartialResponse {

    private List<SkillAttributePartialResponse> tradeSkills;
    private List<SkillAttributePartialResponse> combatSkills;
}
