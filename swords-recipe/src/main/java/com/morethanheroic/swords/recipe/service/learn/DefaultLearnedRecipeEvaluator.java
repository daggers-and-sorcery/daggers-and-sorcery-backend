package com.morethanheroic.swords.recipe.service.learn;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.repository.dao.RecipeDatabaseEntity;
import com.morethanheroic.swords.recipe.repository.domain.RecipeMapper;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide a way to query for the learned recipes of the user.
 */
@Service
@Primary
public class DefaultLearnedRecipeEvaluator implements LearnedRecipeEvaluator {

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @Autowired
    private RecipeMapper recipeMapper;

    public List<RecipeDefinition> getLearnedRecipes(UserEntity userEntity, RecipeType recipeType) {
        final List<RecipeDatabaseEntity> recipes = recipeMapper.findRecipes(userEntity.getId());

        final List<RecipeDefinition> result = new ArrayList<>();
        for (RecipeDatabaseEntity recipeDatabaseEntity : recipes) {
            final RecipeDefinition recipeDefinition = recipeDefinitionCache.getDefinition(recipeDatabaseEntity.getRecipeId());

            if (recipeDefinition.getType() == recipeType) {
                result.add(recipeDefinition);
            }
        }

        return result;
    }

    public boolean hasRecipeLearned(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        return recipeMapper.findRecipe(userEntity.getId(), recipeDefinition.getId()) != null;
    }
}
