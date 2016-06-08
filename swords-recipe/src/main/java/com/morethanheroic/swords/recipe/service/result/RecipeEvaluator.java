package com.morethanheroic.swords.recipe.service.result;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeEvaluator {

    private final RecipeIngredientsRemover recipeIngredientsRemover;
    private final RecipeResultAwarder recipeResultAwarder;
    private final RecipeExperienceAwarder recipeExperienceAwarder;
    private final Random random;

    public boolean evaluateResult(InventoryEntity inventoryEntity, SkillEntity skillEntity, RecipeDefinition recipeDefinition) {
        final boolean isSuccessfulAttempt = isSuccessful(recipeDefinition);

        recipeIngredientsRemover.removeIngredients(inventoryEntity, recipeDefinition);
        if (isSuccessfulAttempt) {
            recipeResultAwarder.awardRewards(inventoryEntity, recipeDefinition);
        }
        recipeExperienceAwarder.awardExperience(skillEntity, recipeDefinition);

        return isSuccessfulAttempt;
    }

    private boolean isSuccessful(RecipeDefinition recipeDefinition) {
        return random.nextInt(100) + 1 >= 100 - recipeDefinition.getChance();
    }
}
