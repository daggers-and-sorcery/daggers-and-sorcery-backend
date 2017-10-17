package com.morethanheroic.swords.skill.tailoring.weaving.service;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.result.RecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.tailoring.weaving.service.domain.WeavingResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TailoringWeavingService {

    private static final int WEAVING_MOVEMENT_POINT_COST = 1;

    private final RecipeEvaluator tailoringLearnedRecipeEvaluator;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final SkillEntityFactory skillEntityFactory;
    private final RecipeIngredientEvaluator recipeIngredientEvaluator;
    private final RecipeRequirementEvaluator recipeRequirementEvaluator;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public WeavingResult create(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        if (recipeDefinition == null || recipeDefinition.getType() != RecipeType.TAILORING_WEAVING) {
            return WeavingResult.INVALID_RECIPE;
        }

        if (!recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)) {
            return WeavingResult.MISSING_INGREDIENTS;
        }

        if (!recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)) {
            return WeavingResult.MISSING_REQUIREMENTS;
        }

        if (userEntity.getMovementPoints() <= 0) {
            return WeavingResult.NOT_ENOUGH_MOVEMENT;
        }

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity);

        final boolean isSuccessful = tailoringLearnedRecipeEvaluator.evaluateResult(userEntity, inventoryEntity, skillEntity, recipeDefinition);

        userBasicAttributeManipulator.decreaseMovement(userEntity, WEAVING_MOVEMENT_POINT_COST);

        return isSuccessful ? WeavingResult.SUCCESSFUL : WeavingResult.UNSUCCESSFUL;
    }
}
