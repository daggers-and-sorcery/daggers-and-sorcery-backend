package com.morethanheroic.swords.recipe.service.response.requirement.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeItemRequirementPartialResponse extends RecipeRequirementPartialResponse {

    private final String itemName;
    private final int requiredAmount;
    private final int existingAmount;
}
