package com.morethanheroic.swords.recipe.service.response.ingredient;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.service.response.ingredient.domain.RecipeIngredientListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.ingredient.domain.RecipeIngredientPartialResponse;
import com.morethanheroic.swords.recipe.service.response.ingredient.domain.RecipeIngredientPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeIngredientListPartialResponseBuilder implements PartialResponseCollectionBuilder<RecipeIngredientListPartialResponseBuilderConfiguration> {

    private final RecipeIngredientPartialResponseBuilder recipeIngredientPartialResponseBuilder;

    @Override
    public List<RecipeIngredientPartialResponse> build(RecipeIngredientListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return responseBuilderConfiguration.getRecipeIngredients().stream()
                .map(recipeIngredient -> recipeIngredientPartialResponseBuilder.build(
                        RecipeIngredientPartialResponseBuilderConfiguration.builder()
                                .userEntity(responseBuilderConfiguration.getUserEntity())
                                .recipeIngredient(recipeIngredient)
                                .build()
                        )
                )
                .collect(Collectors.toList());
    }
}
