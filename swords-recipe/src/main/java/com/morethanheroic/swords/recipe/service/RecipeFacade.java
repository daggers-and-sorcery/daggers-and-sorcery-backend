package com.morethanheroic.swords.recipe.service;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeEntity;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.sun.istack.internal.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides an easy to use api to the recipe module.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeFacade {

    @NotNull
    private final RecipeDefinitionCache recipeDefinitionCache;

    public RecipeDefinition getDefinition(int recipeId) {
        return recipeDefinitionCache.getDefinition(recipeId);
    }

    public RecipeEntity getEntity(UserEntity userEntity, int recipeId) {
        //todo
        return null;
    }

    public void learnRecipe(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        //todo
    }

    public boolean hasRecipeLearned(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        //todo
        return false;
    }
}
