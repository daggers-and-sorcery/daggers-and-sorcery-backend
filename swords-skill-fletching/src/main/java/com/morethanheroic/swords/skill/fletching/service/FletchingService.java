package com.morethanheroic.swords.skill.fletching.service;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.skill.fletching.domain.FletchingResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FletchingService {

    private final RecipeIngredientEvaluator recipeIngredientEvaluator;
    private final RecipeRequirementEvaluator recipeRequirementEvaluator;
    private final LearnedRecipeEvaluator learnedRecipeEvaluator;

    private final FletchingEvaluator fletchingEvaluator;

    @Transactional
    public FletchingResult fletch(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        if (canFletch(userEntity, recipeDefinition)) {
            log.info("User was unable to fetch the recipe: " + recipeDefinition + ".");

            return FletchingResult.UNABLE_TO_FETCH;
        }

        return fletchingEvaluator.doFletching(userEntity, recipeDefinition);
    }

    private boolean canFletch(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        return recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)
                && recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)
                && learnedRecipeEvaluator.hasRecipeLearned(userEntity, recipeDefinition)
                && userEntity.getMovementPoints() > 0;
    }
}
