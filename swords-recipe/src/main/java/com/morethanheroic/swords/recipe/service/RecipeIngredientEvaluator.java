package com.morethanheroic.swords.recipe.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeIngredientEvaluator {

    @Autowired
    private InventoryFacade inventoryFacade;

    public boolean hasIngredients(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);

        for (RecipeIngredient recipeIngredient : recipeDefinition.getRecipeIngredients()) {
            if (inventoryEntity.getItemAmount(recipeIngredient.getId()) < recipeIngredient.getAmount()) {
                return false;
            }
        }

        return true;
    }
}
