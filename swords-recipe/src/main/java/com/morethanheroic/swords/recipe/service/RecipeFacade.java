package com.morethanheroic.swords.recipe.service;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeEntity;
import com.morethanheroic.swords.recipe.repository.domain.RecipeMapper;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides an easy to use api to the recipe module.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeFacade {

    @NonNull
    private final RecipeDefinitionCache recipeDefinitionCache;

    @NonNull
    private final RecipeMapper recipeMapper;

    public RecipeDefinition getDefinition(int recipeId) {
        return recipeDefinitionCache.getDefinition(recipeId);
    }

    public RecipeEntity getEntity(UserEntity userEntity, int recipeId) {
        return new RecipeEntity(recipeDefinitionCache.getDefinition(recipeId), recipeMapper.findRecipe(userEntity.getId(), recipeId));
    }

    public List<RecipeEntity> getAllLearnedRecipes(UserEntity userEntity) {
        return recipeMapper.findRecipes(userEntity.getId()).stream().map(recipeDatabaseEntity -> new RecipeEntity(getDefinition(
                recipeDatabaseEntity.getRecipeId()), recipeDatabaseEntity)).collect(Collectors.toList());
    }

    public void learnRecipe(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        recipeMapper.insertRecipe(userEntity.getId(), recipeDefinition.getId());
    }

    public boolean hasRecipeLearned(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        return recipeMapper.findRecipe(userEntity.getId(), recipeDefinition.getId()) != null;
    }
}
