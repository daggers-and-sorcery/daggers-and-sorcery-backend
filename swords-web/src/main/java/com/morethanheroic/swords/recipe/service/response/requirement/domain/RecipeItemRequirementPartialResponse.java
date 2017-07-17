package com.morethanheroic.swords.recipe.service.response.requirement.domain;

import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeItemRequirementPartialResponse extends RecipeRequirementPartialResponse {

    private final ItemDefinitionPartialResponse item;
    private final int requiredAmount;
    private final int existingAmount;
}
