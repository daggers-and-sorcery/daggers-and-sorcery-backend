package com.morethanheroic.swords.recipe.service.response.ingredient;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.recipe.service.response.ingredient.domain.RecipeIngredientListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.ingredient.domain.RecipeIngredientPartialResponse;
import com.morethanheroic.swords.recipe.service.response.ingredient.domain.RecipeIngredientPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeIngredientListPartialResponseBuilder implements PartialResponseCollectionBuilder<RecipeIngredientListPartialResponseBuilderConfiguration> {

    private final RecipeIngredientPartialResponseBuilder recipeIngredientPartialResponseBuilder;

    @Override
    public List<RecipeIngredientPartialResponse> build(RecipeIngredientListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<RecipeIngredientPartialResponse> result = new ArrayList<>();

        final List<RecipeIngredient> recipeIngredients = responseBuilderConfiguration.getRecipeIngredients();
        for (RecipeIngredient recipeIngredient : recipeIngredients) {
            result.add(
                    recipeIngredientPartialResponseBuilder.build(
                            RecipeIngredientPartialResponseBuilderConfiguration.builder()
                                    .recipeIngredient(recipeIngredient)
                                    .build()
                    )
            );
        }

        return result;
    }
}
