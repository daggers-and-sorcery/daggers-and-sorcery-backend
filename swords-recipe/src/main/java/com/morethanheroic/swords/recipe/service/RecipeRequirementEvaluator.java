package com.morethanheroic.swords.recipe.service;

import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeItemRequirement;
import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.recipe.domain.RecipeSkillRequirement;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeRequirementEvaluator {

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Autowired
    private InventoryFacade inventoryFacade;

    public boolean hasRequirements(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        for (RecipeRequirement recipeRequirement : recipeDefinition.getRecipeRequirements()) {
            if (recipeRequirement instanceof RecipeSkillRequirement) {
                final RecipeSkillRequirement recipeSkillRequirement = (RecipeSkillRequirement) recipeRequirement;

                if (skillEntityFactory.getSkillEntity(userEntity).getLevel(recipeSkillRequirement.getSkill()) < recipeSkillRequirement.getAmount()) {
                    return false;
                }
            } else if (recipeRequirement instanceof RecipeItemRequirement) {
                final RecipeItemRequirement recipeItemRequirement = (RecipeItemRequirement) recipeRequirement;

                if (!inventoryFacade.getInventory(userEntity).hasItemAmount(recipeItemRequirement.getItem(), recipeItemRequirement.getAmount())) {
                    return false;
                }
            }
        }

        return true;
    }
}
