package com.morethanheroic.swords.recipe.view.response.ingredient.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeIngredientPartialResponse extends PartialResponse {

    private final ItemDefinitionPartialResponse item;
    private final int requiredAmount;
    private final int existingAmount;
}
