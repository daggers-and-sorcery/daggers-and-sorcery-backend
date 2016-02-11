package com.morethanheroic.swords.skill.cooking;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.recipe.domain.RecipeReward;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingFacade {

    private final InventoryFacade inventoryFacade;
    private final SkillFacade skillFacade;

    public void cook(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);

        removeIngredients(inventoryEntity, recipeDefinition);
        awardRewards(inventoryEntity, recipeDefinition);
        awardExperience(userEntity, recipeDefinition);
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

    private void awardExperience(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        final SkillEntity skillEntity = skillFacade.getSkills(userEntity);
        for (RecipeExperience recipeExperience : recipeDefinition.getRecipeExperiences()) {
            skillEntity.addSkillXp(recipeExperience.getSkill(), recipeExperience.getAmount());
        }
    }
}
