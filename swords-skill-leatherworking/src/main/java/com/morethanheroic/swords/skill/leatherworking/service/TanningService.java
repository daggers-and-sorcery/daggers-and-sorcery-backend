package com.morethanheroic.swords.skill.leatherworking.service;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.result.RecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.leatherworking.domain.TanningResult;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TanningService {

    private static final int TANNING_MOVEMENT_POINT_COST = 1;

    @Autowired
    private RecipeEvaluator recipeEvaluator;

    @Autowired
    private InventoryEntityFactory inventoryEntityFactory;

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Autowired
    private RecipeIngredientEvaluator recipeIngredientEvaluator;

    @Autowired
    private RecipeRequirementEvaluator recipeRequirementEvaluator;

    @Autowired
    private UserBasicAttributeManipulator userBasicAttributeManipulator;

    @Transactional
    public TanningResult tan(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        if (recipeDefinition == null) {
            return TanningResult.INVALID_EVENT;
        }

        if (!recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)) {
            return TanningResult.MISSING_INGREDIENTS;
        }

        if (!recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)) {
            return TanningResult.MISSING_REQUIREMENTS;
        }

        if (userEntity.getMovementPoints() <= 0) {
            return TanningResult.NOT_ENOUGH_MOVEMENT;
        }

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity);

        final boolean isSuccessful = recipeEvaluator.evaluateResult(userEntity, inventoryEntity, skillEntity, recipeDefinition);

        userBasicAttributeManipulator.decreaseMovement(userEntity, TANNING_MOVEMENT_POINT_COST);

        return isSuccessful ? TanningResult.SUCCESSFUL : TanningResult.UNSUCCESSFUL;
    }
}
