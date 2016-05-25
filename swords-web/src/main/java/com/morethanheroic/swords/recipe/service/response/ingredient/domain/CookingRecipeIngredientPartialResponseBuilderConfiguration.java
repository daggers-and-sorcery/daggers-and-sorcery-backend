package com.morethanheroic.swords.recipe.service.response.ingredient.domain;

import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeIngredientPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final RecipeIngredient recipeIngredient;
}
