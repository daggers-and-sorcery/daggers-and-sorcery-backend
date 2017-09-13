package com.morethanheroic.swords.recipe.service.result;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RecipeEvaluator {

    private final RecipeIngredientsRemover recipeIngredientsRemover;
    private final RecipeRequirementsRemover recipeRequirementsRemover;
    private final RecipeResultAwarder recipeResultAwarder;
    private final RecipeExperienceAwarder recipeExperienceAwarder;
    private final Random random;

    @Transactional(propagation = Propagation.MANDATORY)
    public boolean evaluateResult(UserEntity userEntity, InventoryEntity inventoryEntity, SkillEntity skillEntity, RecipeDefinition recipeDefinition) {
        final boolean isSuccessfulAttempt = isSuccessful(recipeDefinition);

        recipeIngredientsRemover.removeIngredients(inventoryEntity, recipeDefinition);
        recipeRequirementsRemover.removeRequirements(userEntity, recipeDefinition);

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
