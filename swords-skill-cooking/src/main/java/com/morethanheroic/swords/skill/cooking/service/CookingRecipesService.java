package com.morethanheroic.swords.skill.cooking.service;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.result.RecipeEvaluator;
import com.morethanheroic.swords.skill.cooking.domain.CookingResult;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CookingRecipesService {

    private static final int COOKING_MOVEMENT_POINT_COST = 1;

    private final InventoryEntityFactory inventoryEntityFactory;
    private final SkillEntityFactory skillEntityFactory;
    private final RecipeIngredientEvaluator recipeIngredientEvaluator;
    private final RecipeRequirementEvaluator recipeRequirementEvaluator;
    private final LearnedRecipeEvaluator learnedRecipeEvaluator;
    private final RecipeEvaluator recipeEvaluator;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;

    @Transactional
    public CookingResult cook(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        if (!canCook(userEntity, recipeDefinition)) {
            return CookingResult.UNABLE_TO_COOK;
        }

        userBasicAttributeManipulator.decreaseMovement(userEntity, COOKING_MOVEMENT_POINT_COST);

        return doCooking(userEntity, inventoryEntityFactory.getEntity(userEntity), skillEntityFactory.getEntity(userEntity), recipeDefinition);
    }

    private boolean canCook(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        return recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)
                && recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)
                && learnedRecipeEvaluator.hasRecipeLearned(userEntity, recipeDefinition)
                && userEntity.getMovementPoints() > 0;
    }

    private CookingResult doCooking(UserEntity userEntity, InventoryEntity inventoryEntity, SkillEntity skillEntity, RecipeDefinition recipeDefinition) {
        final boolean isSuccessfulAttempt = recipeEvaluator.evaluateResult(userEntity, inventoryEntity, skillEntity, recipeDefinition);

        return isSuccessfulAttempt ? CookingResult.SUCCESSFUL : CookingResult.UNSUCCESSFUL;
    }
}
