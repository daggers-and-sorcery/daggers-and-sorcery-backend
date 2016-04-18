package com.morethanheroic.swords.skill.cooking.service.response.requirement.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeSkillRequirementPartialResponse extends CookingRecipeRequirementPartialResponse {

    private final String skill;
    private final int amount;
}
