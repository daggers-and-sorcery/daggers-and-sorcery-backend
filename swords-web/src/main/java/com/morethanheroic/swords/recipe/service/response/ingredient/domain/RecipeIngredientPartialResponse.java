package com.morethanheroic.swords.recipe.service.response.ingredient.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeIngredientPartialResponse extends PartialResponse {

    private final String name;
    private final int requiredAmount;
    private final int existingAmount;
}
