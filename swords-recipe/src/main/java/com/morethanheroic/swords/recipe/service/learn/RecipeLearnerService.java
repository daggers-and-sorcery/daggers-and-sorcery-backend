package com.morethanheroic.swords.recipe.service.learn;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.repository.domain.RecipeMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Enable a way for the user to learn a recipe.
 */
@Service
@RequiredArgsConstructor
public class RecipeLearnerService {

    private final RecipeMapper recipeMapper;

    public void learnRecipe(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        recipeMapper.insertRecipe(userEntity.getId(), recipeDefinition.getId());
    }
}
