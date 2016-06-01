package com.morethanheroic.swords.recipe.service.result;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeEvaluator {

    private final RecipeIngredientsRemover recipeIngredientsRemover;
    private final RecipeResultAwarder recipeResultAwarder;
    private final RecipeExperienceAwarder recipeExperienceAwarder;

    public void evaluateResult(InventoryEntity inventoryEntity, SkillEntity skillEntity, RecipeDefinition recipeDefinition, boolean isSuccessfulAttempt) {
        recipeIngredientsRemover.removeIngredients(inventoryEntity, recipeDefinition);
        if (isSuccessfulAttempt) {
            recipeResultAwarder.awardRewards(inventoryEntity, recipeDefinition);
        }
        recipeExperienceAwarder.awardExperience(skillEntity, recipeDefinition);
    }
}
