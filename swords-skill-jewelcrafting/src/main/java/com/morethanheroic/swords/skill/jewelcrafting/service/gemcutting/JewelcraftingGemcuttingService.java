package com.morethanheroic.swords.skill.jewelcrafting.service.gemcutting;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.result.RecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.jewelcrafting.service.gemcutting.domain.GemcuttingResult;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service is responsible for the handling of the gemcutting tasks in the Jewelcrafting skill.
 */
@Service
@RequiredArgsConstructor
public class JewelcraftingGemcuttingService {

    private static final int GAMECUTTING_MOVEMENT_POINT_COST = 1;

    private final RecipeEvaluator recipeEvaluator;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final SkillEntityFactory skillEntityFactory;
    private final RecipeIngredientEvaluator recipeIngredientEvaluator;
    private final RecipeRequirementEvaluator recipeRequirementEvaluator;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;

    /**
     * Contains the logic of gamecutting.
     *
     * @param userEntity the user who attempt to cut a gem
     * @param recipeDefinition the recipe used in gamecutting
     * @return the result of the gamecutting
     */
    @Transactional
    public GemcuttingResult cut(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        if (recipeDefinition == null || recipeDefinition.getType() != RecipeType.JEWELCRAFTING_GAMECUTTING) {
            return GemcuttingResult.INVALID_EVENT;
        }

        if (!recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)) {
            return GemcuttingResult.MISSING_INGREDIENTS;
        }

        if (!recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)) {
            return GemcuttingResult.MISSING_REQUIREMENTS;
        }

        if (userEntity.getMovementPoints() < GAMECUTTING_MOVEMENT_POINT_COST) {
            return GemcuttingResult.NOT_ENOUGH_MOVEMENT;
        }

        return calculateCutting(userEntity, recipeDefinition);
    }

    private GemcuttingResult calculateCutting(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity);

        final boolean isSuccessful = recipeEvaluator.evaluateResult(inventoryEntity, skillEntity, recipeDefinition);

        userBasicAttributeManipulator.decreaseMovement(userEntity, GAMECUTTING_MOVEMENT_POINT_COST);

        return isSuccessful ? GemcuttingResult.SUCCESSFUL : GemcuttingResult.UNSUCCESSFUL;
    }
}
