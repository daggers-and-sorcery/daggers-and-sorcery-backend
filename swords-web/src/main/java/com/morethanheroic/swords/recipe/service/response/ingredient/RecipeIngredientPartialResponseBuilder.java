package com.morethanheroic.swords.recipe.service.response.ingredient;

import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.service.response.ingredient.domain.RecipeIngredientPartialResponse;
import com.morethanheroic.swords.recipe.service.response.ingredient.domain.RecipeIngredientPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeIngredientPartialResponseBuilder implements PartialResponseBuilder<RecipeIngredientPartialResponseBuilderConfiguration> {

    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public RecipeIngredientPartialResponse build(RecipeIngredientPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeIngredient recipeIngredient = responseBuilderConfiguration.getRecipeIngredient();

        return RecipeIngredientPartialResponse.builder()
                .name(itemDefinitionCache.getDefinition(recipeIngredient.getId()).getName())
                .amount(recipeIngredient.getAmount())
                .build();
    }
}
