package com.morethanheroic.swords.recipe.service.loader;

import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RecipeDefinitionLoader implements DefinitionLoader<RecipeDefinition> {

    @Override
    public List<RecipeDefinition> loadDefinitions() throws IOException {
        return null;
    }
}
