package com.morethanheroic.swords.skill.cooking.service;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.ingredient.CookingRecipeIngredientListPartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.domain.CookingRecipePartialResponse;
import com.morethanheroic.swords.skill.cooking.service.ingredient.domain.CookingRecipeIngredientListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.domain.configuration.CookingRecipePartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingRecipePartialResponseBuilder implements PartialResponseBuilder<CookingRecipePartialResponseBuilderConfiguration> {

    private final CookingRecipeIngredientListPartialResponseBuilder cookingRecipeIngredientListPartialResponseBuilder;

    @Override
    public CookingRecipePartialResponse build(CookingRecipePartialResponseBuilderConfiguration cookingRecipePartialResponseBuilderConfiguration) {
        final RecipeDefinition recipeDefinition = cookingRecipePartialResponseBuilderConfiguration.getRecipeDefinition();

        return CookingRecipePartialResponse.builder()
                .id(recipeDefinition.getId())
                .chance(recipeDefinition.getChance())
                .recipeIngredients(
                        cookingRecipeIngredientListPartialResponseBuilder.build(
                                CookingRecipeIngredientListPartialResponseBuilderConfiguration.builder()
                                .recipeIngredients(recipeDefinition.getRecipeIngredients())
                                .build()
                        )
                )
                .build();
    }
}
