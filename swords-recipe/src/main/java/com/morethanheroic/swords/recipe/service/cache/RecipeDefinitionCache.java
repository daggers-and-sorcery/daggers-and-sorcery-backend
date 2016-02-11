package com.morethanheroic.swords.recipe.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.loader.RecipeDefinitionLoader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Store the {@link RecipeDefinition}s in a cached manner.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeDefinitionCache implements DefinitionCache<Integer, RecipeDefinition> {

    @NonNull
    private final RecipeDefinitionLoader recipeDefinitionLoader;

    private final Map<Integer, RecipeDefinition> recipeDefinitionMap = new HashMap<>();

    @PostConstruct
    private void initialize() throws IOException {
        final List<RecipeDefinition> recipeDefinitionList = recipeDefinitionLoader.loadDefinitions();

        log.info("Loaded " + recipeDefinitionList.size() + " recipe definitions.");

        for (RecipeDefinition recipeDefinition : recipeDefinitionList) {
            recipeDefinitionMap.put(recipeDefinition.getId(), recipeDefinition);
        }
    }

    @Override
    public RecipeDefinition getDefinition(Integer id) {
        return recipeDefinitionMap.get(id);
    }

    public boolean hasDefinition(Integer id) {
        return recipeDefinitionMap.containsKey(id);
    }
}
