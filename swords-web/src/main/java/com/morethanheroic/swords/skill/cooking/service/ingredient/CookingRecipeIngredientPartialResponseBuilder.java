package com.morethanheroic.swords.skill.cooking.service.ingredient;

import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.ingredient.domain.CookingRecipeIngredientPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.ingredient.domain.CookingRecipeIngredientPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingRecipeIngredientPartialResponseBuilder implements PartialResponseBuilder<CookingRecipeIngredientPartialResponseBuilderConfiguration> {

    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public CookingRecipeIngredientPartialResponse build(CookingRecipeIngredientPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeIngredient recipeIngredient = responseBuilderConfiguration.getRecipeIngredient();

        return CookingRecipeIngredientPartialResponse.builder()
                .name(itemDefinitionCache.getDefinition(recipeIngredient.getId()).getName())
                .amount(recipeIngredient.getAmount())
                .build();
    }
}
