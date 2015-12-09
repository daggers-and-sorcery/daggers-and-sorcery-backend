package com.morethanheroic.swords.recipe.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import org.springframework.stereotype.Service;

@Service
public class RecipeDefinitionCache implements DefinitionCache<Integer, RecipeDefinition> {

    @Override
    public RecipeDefinition getDefinition(Integer key) {
        return null;
    }
}
