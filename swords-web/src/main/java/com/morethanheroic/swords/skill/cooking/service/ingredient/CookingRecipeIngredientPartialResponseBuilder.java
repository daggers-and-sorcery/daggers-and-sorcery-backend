package com.morethanheroic.swords.skill.cooking.service.ingredient;

import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.ingredient.domain.CookingRecipeIngredientPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.ingredient.domain.CookingRecipeIngredientPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class CookingRecipeIngredientPartialResponseBuilder implements PartialResponseBuilder<CookingRecipeIngredientPartialResponseBuilderConfiguration> {

    @Override
    public CookingRecipeIngredientPartialResponse build(CookingRecipeIngredientPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeIngredient recipeIngredient = responseBuilderConfiguration.getRecipeIngredient();

        return CookingRecipeIngredientPartialResponse.builder()
                .id(recipeIngredient.getId())
                .amount(recipeIngredient.getAmount())
                .build();
    }
}
