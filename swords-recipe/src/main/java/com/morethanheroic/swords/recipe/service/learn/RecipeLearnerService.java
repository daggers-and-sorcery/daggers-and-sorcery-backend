package com.morethanheroic.swords.recipe.service.learn;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.repository.domain.RecipeMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Enable a way for the user to learn a recipe.
 */
@Service
public class RecipeLearnerService {

    @Autowired
    private RecipeMapper recipeMapper;

    public void learnRecipe(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        recipeMapper.insertRecipe(userEntity.getId(), recipeDefinition.getId());
    }
}
