package com.morethanheroic.swords.recipe.service.response.ingredient.domain;

import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipeIngredientListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final List<RecipeIngredient> recipeIngredients;
}
