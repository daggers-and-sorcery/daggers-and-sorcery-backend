package com.morethanheroic.swords.skill.fletching.service;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
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
        if (recipeDefinition == null) {
            log.info("User was unable to fetch the recipe because it doesn't exists.");

            return FletchingResult.INVALID_EVENT;
        }

        if (recipeDefinition.getType() != RecipeType.FLETCHING) {
            log.warn("The user tried to craft with fletching the recipe: " + recipeDefinition + " but it's not a fletching recipe!");

            return FletchingResult.INVALID_EVENT;
        }

        if (!learnedRecipeEvaluator.hasRecipeLearned(userEntity, recipeDefinition)) {
            log.info("User doesn't have the target recipe learned: " + recipeDefinition + ".");

            return FletchingResult.INVALID_EVENT;
        }

        if (!recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)) {
            log.info("User was unable to fetch the recipe: " + recipeDefinition + ".");

            return FletchingResult.MISSING_INGREDIENTS;
        }

        if (!recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)) {
            log.info("User was unable to fetch the recipe: " + recipeDefinition + ".");

            return FletchingResult.MISSING_REQUIREMENTS;
        }

        if (userEntity.getMovementPoints() <= 0) {
            log.info("User was unable to fetch the recipe: " + recipeDefinition + ".");

            return FletchingResult.NOT_ENOUGH_MOVEMENT;
        }

        return fletchingEvaluator.doFletching(userEntity, recipeDefinition);
    }
}
