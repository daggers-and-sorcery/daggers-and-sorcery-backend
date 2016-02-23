package com.morethanheroic.swords.skill.cooking.service.validator;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.recipe.service.RecipeFacade;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.response.exception.ConflictException;
import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.swords.skill.cooking.view.request.CookingCreateRequest;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingCreateRequestValidator {

    private final RecipeDefinitionCache recipeDefinitionCache;
    private final RecipeFacade recipeFacade;
    private final SkillFacade skillFacade;
    private final InventoryFacade inventoryFacade;

    public void validate(UserEntity userEntity, CookingCreateRequest cookingCreateRequest) {
        if (!recipeDefinitionCache.hasDefinition(cookingCreateRequest.getRecipeId())) {
            throw new NotFoundException();
        }

        final RecipeDefinition recipeDefinition = recipeDefinitionCache.getDefinition(cookingCreateRequest.getRecipeId());
        if (!recipeFacade.hasRecipeLearned(userEntity, recipeDefinition)) {
            throw new ConflictException();
        }

        final SkillEntity skillEntity = skillFacade.getSkills(userEntity);
        for (RecipeRequirement recipeRequirement : recipeDefinition.getRecipeRequirements()) {
            if (skillEntity.getSkillLevel(recipeRequirement.getSkill()) < recipeRequirement.getAmount()) {
                throw new ConflictException();
            }
        }

        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);
        for (RecipeIngredient recipeIngredient : recipeDefinition.getRecipeIngredients()) {
            if (inventoryEntity.getItemAmount(recipeIngredient.getId()) < recipeIngredient.getAmount()) {
                throw new ConflictException();
            }
        }
    }
}
