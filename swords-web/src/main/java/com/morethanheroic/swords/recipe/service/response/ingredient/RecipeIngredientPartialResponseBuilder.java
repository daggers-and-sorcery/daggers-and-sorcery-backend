package com.morethanheroic.swords.recipe.service.response.ingredient;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.recipe.service.response.ingredient.domain.RecipeIngredientPartialResponse;
import com.morethanheroic.swords.recipe.service.response.ingredient.domain.RecipeIngredientPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeIngredientPartialResponseBuilder implements PartialResponseBuilder<RecipeIngredientPartialResponseBuilderConfiguration> {

    private final ItemDefinitionCache itemDefinitionCache;
    private final InventoryEntityFactory inventoryEntityFactory;

    @Override
    public RecipeIngredientPartialResponse build(RecipeIngredientPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeIngredient recipeIngredient = responseBuilderConfiguration.getRecipeIngredient();

        return RecipeIngredientPartialResponse.builder()
                .name(itemDefinitionCache.getDefinition(recipeIngredient.getId()).getName())
                .requiredAmount(recipeIngredient.getAmount())
                .existingAmount(inventoryEntityFactory.getEntity(responseBuilderConfiguration.getUserEntity()).getItemAmount(itemDefinitionCache.getDefinition(recipeIngredient.getId())))
                .build();
    }
}
