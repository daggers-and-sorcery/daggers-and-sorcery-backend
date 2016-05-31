package com.morethanheroic.swords.recipe.service.result;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeReward;
import org.springframework.stereotype.Service;

@Service
public class RecipeResultAwarder {

    public void awardRewards(InventoryEntity inventoryEntity, RecipeDefinition recipeDefinition) {
        for (RecipeReward recipeReward : recipeDefinition.getRecipeRewards()) {
            inventoryEntity.addItem(recipeReward.getId(), recipeReward.getAmount());
        }
    }
}
