package com.morethanheroic.swords.recipe.service;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.recipe.service.learn.DefaultLearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.learn.RecipeLearnerService;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides an easy to use api to the recipe module.
 *
 * @deprecated Use the specific classes instead.
 */
@Service
@Deprecated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeFacade {

    @NonNull
    private final RecipeDefinitionCache recipeDefinitionCache;

    @NonNull
    private final RecipeLearnerService recipeLearnerService;

    @NonNull
    private final DefaultLearnedRecipeEvaluator learnedRecipeEvaluator;

    @Deprecated
    public RecipeDefinition getDefinition(int recipeId) {
        return recipeDefinitionCache.getDefinition(recipeId);
    }

    @Deprecated
    public void learnRecipe(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        recipeLearnerService.learnRecipe(userEntity, recipeDefinition);
    }

    @Deprecated
    public boolean hasRecipeLearned(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        return learnedRecipeEvaluator.hasRecipeLearned(userEntity, recipeDefinition);
    }
}
