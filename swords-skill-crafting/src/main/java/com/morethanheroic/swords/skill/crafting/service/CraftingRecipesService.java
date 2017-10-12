package com.morethanheroic.swords.skill.crafting.service;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.result.RecipeEvaluator;
import com.morethanheroic.swords.skill.crafting.domain.CraftingResult;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CraftingRecipesService {

    private static final int CRAFTING_MOVEMENT_POINT_COST = 1;

    private final RecipeEvaluator recipeEvaluator;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final SkillEntityFactory skillEntityFactory;
    private final RecipeIngredientEvaluator recipeIngredientEvaluator;
    private final RecipeRequirementEvaluator recipeRequirementEvaluator;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;
    private final LearnedRecipeEvaluator learnedRecipeEvaluator;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CraftingResult craft(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        if (recipeDefinition == null || (recipeDefinition.getType() != RecipeType.CRAFTING && recipeDefinition.getType() != RecipeType.CRAFTING_RESOURCE) || !learnedRecipeEvaluator.hasRecipeLearned(userEntity, recipeDefinition)) {
            return CraftingResult.INVALID_EVENT;
        }

        if (!recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)) {
            return CraftingResult.MISSING_INGREDIENTS;
        }

        if (!recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)) {
            return CraftingResult.MISSING_REQUIREMENTS;
        }

        if (userEntity.getMovementPoints() < CRAFTING_MOVEMENT_POINT_COST) {
            return CraftingResult.NOT_ENOUGH_MOVEMENT;
        }

        return calculateCraft(userEntity, recipeDefinition);
    }

    private CraftingResult calculateCraft(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        userBasicAttributeManipulator.decreaseMovement(userEntity, CRAFTING_MOVEMENT_POINT_COST);

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity);
        final boolean isSuccessfulAttempt = recipeEvaluator.evaluateResult(userEntity, inventoryEntity, skillEntity, recipeDefinition);

        return isSuccessfulAttempt ? CraftingResult.SUCCESSFUL : CraftingResult.UNSUCCESSFUL;
    }
}
