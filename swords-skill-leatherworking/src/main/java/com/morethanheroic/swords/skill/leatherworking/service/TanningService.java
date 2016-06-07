package com.morethanheroic.swords.skill.leatherworking.service;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
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

import java.util.Random;

@Service
public class TanningService {

    private static final int TANNING_MOVEMENT_POINT_COST = 1;

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
    private Random random;

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

        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

        final boolean isSuccessful = isSuccessful(recipeDefinition);

        recipeEvaluator.evaluateResult(inventoryEntity, skillEntity, recipeDefinition, isSuccessful);

        userBasicAttributeManipulator.decreaseMovement(userEntity, TANNING_MOVEMENT_POINT_COST);

        return isSuccessful ? TanningResult.SUCCESSFUL : TanningResult.UNSUCCESSFUL;
    }

    //TODO: rework this to a common recipe evaluator and use the same for cooking too!
    private boolean isSuccessful(RecipeDefinition recipeDefinition) {
        return random.nextInt(100) + 1 >= 100 - recipeDefinition.getChance();
    }
}
