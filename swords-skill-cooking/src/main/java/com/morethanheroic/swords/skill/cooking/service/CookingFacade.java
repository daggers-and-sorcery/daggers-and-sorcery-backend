package com.morethanheroic.swords.skill.cooking.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.*;
import com.morethanheroic.swords.recipe.service.RecipeFacade;
import com.morethanheroic.swords.skill.cooking.domain.CookingResult;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingFacade {

    private final RecipeFacade recipeFacade;
    private final InventoryFacade inventoryFacade;
    private final SkillFacade skillFacade;
    private final Random random;

    @Transactional
    public CookingResult cook(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        if (!canCook(userEntity, recipeDefinition)) {
            return CookingResult.UNABLE_TO_COOK;
        }

        userEntity.setMovementPoints(userEntity.getMovementPoints() - 1);

        return doCooking(inventoryFacade.getInventory(userEntity), skillFacade.getSkills(userEntity), recipeDefinition);
    }

    //TODO: move this to a separate service object
    private boolean canCook(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        final SkillEntity skillEntity = skillFacade.getSkills(userEntity);
        //Has the required skills
        for (RecipeRequirement recipeRequirement : recipeDefinition.getRecipeRequirements()) {
            if (skillEntity.getSkillLevel(recipeRequirement.getSkill()) < recipeRequirement.getAmount()) {
                return false;
            }
        }

        //Has the ingredients
        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);
        for (RecipeIngredient recipeIngredient : recipeDefinition.getRecipeIngredients()) {
            if (inventoryEntity.getItemAmount(recipeIngredient.getId()) < recipeIngredient.getAmount()) {
                return false;
            }
        }

        return userEntity.getMovementPoints() > 0 && recipeFacade.hasRecipeLearned(userEntity, recipeDefinition);
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
            skillEntity.addSkillXp(recipeExperience.getSkill(), recipeExperience.getAmount());
        }
    }
}
