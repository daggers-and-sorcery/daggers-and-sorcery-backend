package com.morethanheroic.swords.recipe.service.response.requirement.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeSkillRequirementPartialResponse extends RecipeRequirementPartialResponse {

    private final String skill;
    private final int amount;
}
