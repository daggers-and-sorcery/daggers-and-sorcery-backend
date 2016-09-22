package com.morethanheroic.swords.profile.response.service.skill.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SkillPartialResponse extends PartialResponse {

    private List<SkillAttributePartialResponse> tradeSkills;
    private List<SkillAttributePartialResponse> combatSkills;
    private List<SkillAttributePartialResponse> magicSkills;
    private List<SkillAttributePartialResponse> shadowSkills;
}
