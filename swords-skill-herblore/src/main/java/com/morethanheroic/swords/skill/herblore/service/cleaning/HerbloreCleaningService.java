package com.morethanheroic.swords.skill.herblore.service.cleaning;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.result.RecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.herblore.service.cleaning.domain.CleaningResult;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service is responsible for the handling of the cleaning tasks in the Herblore skill.
 */
@Service
@RequiredArgsConstructor
public class HerbloreCleaningService {

    private static final int CLEANING_MOVEMENT_POINT_COST = 1;

    private final RecipeEvaluator recipeEvaluator;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final SkillEntityFactory skillEntityFactory;
    private final RecipeIngredientEvaluator recipeIngredientEvaluator;
    private final RecipeRequirementEvaluator recipeRequirementEvaluator;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;

    /**
     * Contains the logic of the herb cleaning.
     *
     * @param userEntity the user who attempt to clean a herb
     * @param recipeDefinition the recipe used in the cleaning
     * @return the result of the cleaning
     */
    @Transactional
    public CleaningResult clean(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        if (recipeDefinition == null || recipeDefinition.getType() != RecipeType.HERBLORE_CLEANING) {
            return CleaningResult.INVALID_EVENT;
        }

        if (!recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)) {
            return CleaningResult.MISSING_INGREDIENTS;
        }

        if (!recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)) {
            return CleaningResult.MISSING_REQUIREMENTS;
        }

        if (userEntity.getMovementPoints() < CLEANING_MOVEMENT_POINT_COST) {
            return CleaningResult.NOT_ENOUGH_MOVEMENT;
        }

        return calculateCleaning(userEntity, recipeDefinition);
    }

    private CleaningResult calculateCleaning(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity);

        final boolean isSuccessful = recipeEvaluator.evaluateResult(userEntity, inventoryEntity, skillEntity, recipeDefinition);

        userBasicAttributeManipulator.decreaseMovement(userEntity, CLEANING_MOVEMENT_POINT_COST);

        return isSuccessful ? CleaningResult.SUCCESSFUL : CleaningResult.UNSUCCESSFUL;
    }
}
