package com.morethanheroic.swords.recipe.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeIngredientEvaluator {

    private final InventoryEntityFactory inventoryEntityFactory;

    public boolean hasIngredients(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);

        return recipeDefinition.getRecipeIngredients().stream()
                .noneMatch(recipeIngredient -> inventoryEntity.getItemAmount(recipeIngredient.getItem()) < recipeIngredient.getAmount());
    }
}
