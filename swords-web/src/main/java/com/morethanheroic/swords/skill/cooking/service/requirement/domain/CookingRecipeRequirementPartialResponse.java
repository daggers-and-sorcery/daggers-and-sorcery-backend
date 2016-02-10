package com.morethanheroic.swords.skill.cooking.service.requirement.domain;

import com.morethanheroic.swords.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.domain.SkillType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeRequirementPartialResponse extends PartialResponse {

    private final SkillType skill;
    private final int amount;
}
