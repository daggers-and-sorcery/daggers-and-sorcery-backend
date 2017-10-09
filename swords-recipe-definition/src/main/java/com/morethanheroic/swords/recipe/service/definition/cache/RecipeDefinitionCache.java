package com.morethanheroic.swords.recipe.service.definition.cache;

import com.morethanheroic.definition.cache.DefinitionCache;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.definition.exception.MissingRecipeException;
import com.morethanheroic.swords.recipe.service.definition.loader.RecipeDefinitionLoader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Store the {@link RecipeDefinition}s in a cached manner.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeDefinitionCache implements DefinitionCache<Integer, RecipeDefinition> {

    @NonNull
    private final RecipeDefinitionLoader recipeDefinitionLoader;

    private final Map<Integer, RecipeDefinition> recipeDefinitionMap = new HashMap<>();

    @PostConstruct
    private void initialize() {
        final List<RecipeDefinition> recipeDefinitionList = recipeDefinitionLoader.loadDefinitions();

        log.info("Loaded " + recipeDefinitionList.size() + " recipe definitions.");

        for (RecipeDefinition recipeDefinition : recipeDefinitionList) {
            recipeDefinitionMap.put(recipeDefinition.getId(), recipeDefinition);
        }
    }

    @Override
    public RecipeDefinition getDefinition(final Integer recipeId) {
        if (hasDefinition(recipeId)) {
            return recipeDefinitionMap.get(recipeId);
        }

        throw new MissingRecipeException(recipeId);
    }

    @Override
    public List<RecipeDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(recipeDefinitionMap.values()));
    }

    @Override
    public boolean isDefinitionExists(Integer key) {
        return recipeDefinitionMap.containsKey(key);
    }

    @Override
    public int getSize() {
        return recipeDefinitionMap.size();
    }

    public boolean hasDefinition(Integer id) {
        return recipeDefinitionMap.containsKey(id);
    }
}
