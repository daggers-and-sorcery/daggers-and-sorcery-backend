package com.morethanheroic.swords.skill.jewelcrafting.service.recipe;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.result.RecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.jewelcrafting.service.recipe.domain.JewelcraftingResult;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JewelcraftingRecipesService {

    private static final int JEWELCRAFTING_MOVEMENT_POINT_COST = 1;

    private final RecipeEvaluator recipeEvaluator;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final SkillEntityFactory skillEntityFactory;
    private final RecipeIngredientEvaluator recipeIngredientEvaluator;
    private final RecipeRequirementEvaluator recipeRequirementEvaluator;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;
    private final LearnedRecipeEvaluator learnedRecipeEvaluator;

    @Transactional
    public JewelcraftingResult craft(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        if (recipeDefinition == null || recipeDefinition.getType() != RecipeType.JEWELCRAFTING || !learnedRecipeEvaluator.hasRecipeLearned(userEntity, recipeDefinition)) {
            return JewelcraftingResult.INVALID_RECIPE;
        }

        if (!recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)) {
            return JewelcraftingResult.MISSING_INGREDIENTS;
        }

        if (!recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)) {
            return JewelcraftingResult.MISSING_REQUIREMENTS;
        }

        if (userEntity.getMovementPoints() < JEWELCRAFTING_MOVEMENT_POINT_COST) {
            return JewelcraftingResult.NOT_ENOUGH_MOVEMENT;
        }

        return calculateCraft(userEntity, recipeDefinition);
    }

    private JewelcraftingResult calculateCraft(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        userBasicAttributeManipulator.decreaseMovement(userEntity, JEWELCRAFTING_MOVEMENT_POINT_COST);

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity);
        final boolean isSuccessfulAttempt = recipeEvaluator.evaluateResult(userEntity, inventoryEntity, skillEntity, recipeDefinition);

        return isSuccessfulAttempt ? JewelcraftingResult.SUCCESSFUL : JewelcraftingResult.UNSUCCESSFUL;
    }
}
