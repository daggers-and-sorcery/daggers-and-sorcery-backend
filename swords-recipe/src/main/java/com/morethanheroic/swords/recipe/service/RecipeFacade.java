package com.morethanheroic.swords.recipe.service;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
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

    public RecipeDefinition getDefinition(int id) {
        return recipeDefinitionCache.getDefinition(id);
    }
}
