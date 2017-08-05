package com.morethanheroic.swords.recipe.view.response.ingredient;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.recipe.view.response.ingredient.domain.RecipeIngredientPartialResponse;
import com.morethanheroic.swords.recipe.view.response.ingredient.domain.RecipeIngredientPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeIngredientPartialResponseBuilder implements PartialResponseBuilder<RecipeIngredientPartialResponseBuilderConfiguration> {

    private final ItemDefinitionCache itemDefinitionCache;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;

    @Override
    public RecipeIngredientPartialResponse build(RecipeIngredientPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeIngredient recipeIngredient = responseBuilderConfiguration.getRecipeIngredient();

        return RecipeIngredientPartialResponse.builder()
                .item(
                        identifiedItemPartialResponseBuilder.build(
                                IdentifiedItemPartialResponseBuilderConfiguration.builder()
                                        .item(itemDefinitionCache.getDefinition(recipeIngredient.getId()))
                                        .build()
                        )
                )
                .requiredAmount(recipeIngredient.getAmount())
                .existingAmount(inventoryEntityFactory.getEntity(responseBuilderConfiguration.getUserEntity()).getItemAmount(itemDefinitionCache.getDefinition(recipeIngredient.getId())))
                .build();
    }
}
