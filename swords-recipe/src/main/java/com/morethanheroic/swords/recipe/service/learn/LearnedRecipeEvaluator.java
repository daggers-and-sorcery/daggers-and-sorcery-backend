package com.morethanheroic.swords.recipe.service.learn;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.user.domain.UserEntity;

import java.util.List;

/**
 * A general blueprint for recipe evaluators.
 */
public interface LearnedRecipeEvaluator {

    List<RecipeDefinition> getLearnedRecipes(UserEntity userEntity, RecipeType recipeType);

    boolean hasRecipeLearned(UserEntity userEntity, RecipeDefinition recipeDefinition);
}
