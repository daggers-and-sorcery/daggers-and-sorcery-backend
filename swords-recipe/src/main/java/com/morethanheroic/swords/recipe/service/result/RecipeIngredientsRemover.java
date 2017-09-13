package com.morethanheroic.swords.recipe.service.result;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import org.springframework.stereotype.Service;

@Service
public class RecipeIngredientsRemover {

    public void removeIngredients(InventoryEntity inventoryEntity, RecipeDefinition recipeDefinition) {
        recipeDefinition.getRecipeIngredients()
                .forEach(recipeIngredient -> inventoryEntity.removeItem(recipeIngredient.getItem(), recipeIngredient.getAmount()));
    }
}
