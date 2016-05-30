package com.morethanheroic.swords.skill.cooking.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.recipe.domain.RecipeReward;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.skill.cooking.domain.CookingResult;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingFacade {

    private final InventoryFacade inventoryFacade;
    private final SkillEntityFactory skillEntityFactory;
    private final Random random;
    private final RecipeIngredientEvaluator recipeIngredientEvaluator;
    private final RecipeRequirementEvaluator recipeRequirementEvaluator;
    private final LearnedRecipeEvaluator learnedRecipeEvaluator;

    @Transactional
    public CookingResult cook(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        if (!canCook(userEntity, recipeDefinition)) {
            return CookingResult.UNABLE_TO_COOK;
        }

        userEntity.setMovementPoints(userEntity.getMovementPoints() - 1);

        return doCooking(inventoryFacade.getInventory(userEntity), skillEntityFactory.getSkillEntity(userEntity), recipeDefinition);
    }

    private boolean canCook(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        return recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)
                && recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)
                && learnedRecipeEvaluator.hasRecipeLearned(userEntity, recipeDefinition)
                && userEntity.getMovementPoints() > 0;
    }

    private boolean isSuccessful(RecipeDefinition recipeDefinition) {
        return random.nextInt(100) + 1 >= 100 - recipeDefinition.getChance();
    }

    private CookingResult doCooking(InventoryEntity inventoryEntity, SkillEntity skillEntity, RecipeDefinition recipeDefinition) {
        final boolean isSuccessfulAttempt = isSuccessful(recipeDefinition);

        removeIngredients(inventoryEntity, recipeDefinition);
        if (isSuccessfulAttempt) {
            awardRewards(inventoryEntity, recipeDefinition);
        }
        awardExperience(skillEntity, recipeDefinition);

        return isSuccessfulAttempt ? CookingResult.SUCCESSFUL : CookingResult.UNSUCCESSFUL;
    }

    private void removeIngredients(InventoryEntity inventoryEntity, RecipeDefinition recipeDefinition) {
        for (RecipeIngredient recipeIngredient : recipeDefinition.getRecipeIngredients()) {
            inventoryEntity.removeItem(recipeIngredient.getId(), recipeIngredient.getAmount());
        }
    }

    private void awardRewards(InventoryEntity inventoryEntity, RecipeDefinition recipeDefinition) {
        for (RecipeReward recipeReward : recipeDefinition.getRecipeRewards()) {
            inventoryEntity.addItem(recipeReward.getId(), recipeReward.getAmount());
        }
    }

    private void awardExperience(SkillEntity skillEntity, RecipeDefinition recipeDefinition) {
        for (RecipeExperience recipeExperience : recipeDefinition.getRecipeExperiences()) {
            skillEntity.increaseExperience(recipeExperience.getSkill(), recipeExperience.getAmount());
        }
    }
}
