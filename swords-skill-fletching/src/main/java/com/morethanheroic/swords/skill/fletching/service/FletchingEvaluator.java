package com.morethanheroic.swords.skill.fletching.service;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.result.RecipeEvaluator;
import com.morethanheroic.swords.skill.fletching.domain.FletchingResult;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FletchingEvaluator {

    private static final int FLETCING_MOVEMENT_POINT_COST = 1;

    private final UserBasicAttributeManipulator userBasicAttributeManipulator;
    private final RecipeEvaluator recipeEvaluator;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final SkillEntityFactory skillEntityFactory;

    public FletchingResult doFletching(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        log.info("User is fletching, creating the recipe: " + recipeDefinition + ".");

        userBasicAttributeManipulator.decreaseMovement(userEntity, FLETCING_MOVEMENT_POINT_COST);

        return calculateFletchingResult(
                recipeEvaluator.evaluateResult(
                        inventoryEntityFactory.getEntity(userEntity.getId()),
                        skillEntityFactory.getEntity(userEntity.getId()),
                        recipeDefinition
                )
        );
    }

    private FletchingResult calculateFletchingResult(final boolean successful) {
        return successful ? FletchingResult.SUCCESSFUL : FletchingResult.UNSUCCESSFUL;
    }
}
