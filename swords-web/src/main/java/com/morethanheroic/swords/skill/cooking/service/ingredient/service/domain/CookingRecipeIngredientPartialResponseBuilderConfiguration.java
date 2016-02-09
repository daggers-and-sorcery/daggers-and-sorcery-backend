package com.morethanheroic.swords.skill.cooking.service.ingredient.service.domain;

import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeIngredientPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final RecipeIngredient recipeIngredient;
}
