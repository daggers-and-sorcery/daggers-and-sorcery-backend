package com.morethanheroic.swords.skill.cooking.service.ingredient.domain;

import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipeIngredientListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final List<RecipeIngredient> recipeIngredients;
}
