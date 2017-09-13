package com.morethanheroic.swords.recipe.view.response.requirement.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeResourceRequirementPartialResponse extends RecipeRequirementPartialResponse {

    private final String resource;
    private final int amount;
}
