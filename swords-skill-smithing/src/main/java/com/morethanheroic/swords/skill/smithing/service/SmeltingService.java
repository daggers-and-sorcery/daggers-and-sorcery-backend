package com.morethanheroic.swords.skill.smithing.service;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.result.RecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.smithing.domain.SmeltingResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SmeltingService {

    private static final int SMELTING_MOVEMENT_POINT_COST = 1;

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

    @Transactional
    public SmeltingResult smelt(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        if (recipeDefinition == null) {
            return SmeltingResult.INVALID_EVENT;
        }

        if (!recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)) {
            return SmeltingResult.MISSING_INGREDIENTS;
        }

        if (!recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)) {
            return SmeltingResult.MISSING_REQUIREMENTS;
        }

        if (userEntity.getMovementPoints() <= 0) {
            return SmeltingResult.NOT_ENOUGH_MOVEMENT;
        }

        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

        final boolean isSuccessful = recipeEvaluator.evaluateResult(inventoryEntity, skillEntity, recipeDefinition);

        userBasicAttributeManipulator.decreaseMovement(userEntity, SMELTING_MOVEMENT_POINT_COST);

        return isSuccessful ? SmeltingResult.SUCCESSFUL : SmeltingResult.UNSUCCESSFUL;
    }
}
