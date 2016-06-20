package com.morethanheroic.swords.skill.smithing.service;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.result.RecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.smithing.domain.SmithingResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SmithingService {

    private static final int SMITHING_MOVEMENT_POINT_COST = 1;

    @Autowired
    private RecipeEvaluator recipeEvaluator;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Autowired
    private RecipeIngredientEvaluator recipeIngredientEvaluator;

    @Autowired
    private RecipeRequirementEvaluator recipeRequirementEvaluator;

    @Autowired
    private UserBasicAttributeManipulator userBasicAttributeManipulator;

    @Autowired
    private LearnedRecipeEvaluator learnedRecipeEvaluator;

    @Transactional
    public SmithingResult smith(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        if (recipeDefinition == null || !learnedRecipeEvaluator.hasRecipeLearned(userEntity, recipeDefinition)) {
            return SmithingResult.INVALID_EVENT;
        }

        if (!recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)) {
            return SmithingResult.MISSING_INGREDIENTS;
        }

        if (!recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)) {
            return SmithingResult.MISSING_REQUIREMENTS;
        }

        if (userEntity.getMovementPoints() <= 0) {
            return SmithingResult.NOT_ENOUGH_MOVEMENT;
        }

        userBasicAttributeManipulator.decreaseMovement(userEntity, SMITHING_MOVEMENT_POINT_COST);

        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);
        final boolean isSuccessfulAttempt = recipeEvaluator.evaluateResult(inventoryEntity, skillEntity, recipeDefinition);

        return isSuccessfulAttempt ? SmithingResult.SUCCESSFUL : SmithingResult.UNSUCCESSFUL;
    }
}
