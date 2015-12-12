package com.morethanheroic.swords.recipe.service.loader;

import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.loader.domain.RawRecipeDefinition;
import com.morethanheroic.swords.recipe.service.transformer.RecipeDefinitionTransformer;
import com.sun.istack.internal.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class load {@link RecipeDefinition}s from xml files.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeDefinitionLoader implements DefinitionLoader<RecipeDefinition> {

    private static final String RECIPE_DEFINITION_LOCATION = "classpath:data/recipe/definition/";
    private static final String RECIPE_SCHEMA_LOCATION = "classpath:data/recipe/schema.xsd";
    private static final int RECIPE_COUNT = 100;

    @NotNull
    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;

    @NotNull
    private final RecipeDefinitionTransformer recipeDefinitionTransformer;

    @Override
    public List<RecipeDefinition> loadDefinitions() throws IOException {
        return loadRawRecipeDefinitions().stream().map(recipeDefinitionTransformer::transform).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public List<RawRecipeDefinition> loadRawRecipeDefinitions() throws IOException {
        return numericXmlDefinitionLoader.loadDefinitions(RawRecipeDefinition.class, RECIPE_DEFINITION_LOCATION, RECIPE_SCHEMA_LOCATION, RECIPE_COUNT);
    }
}
