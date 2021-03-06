package com.morethanheroic.swords.recipe.service.definition.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeDefinition;
import com.morethanheroic.swords.recipe.service.definition.transformer.RecipeDefinitionTransformer;
import com.morethanheroic.xml.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.xml.service.loader.domain.NumericDefinitionLoadingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * This class load {@link RecipeDefinition}s from xml files.
 */
@Service
@RequiredArgsConstructor
public class RecipeDefinitionLoader implements DefinitionLoader<RecipeDefinition> {

    private static final String RECIPE_DEFINITION_LOCATION = "classpath:data/recipe/definition/";
    private static final String RECIPE_SCHEMA_LOCATION = "classpath:data/recipe/schema.xsd";

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final RecipeDefinitionTransformer recipeDefinitionTransformer;

    @Override
    public List<RecipeDefinition> loadDefinitions() {
        return loadRawRecipeDefinitions().stream()
                .map(recipeDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }

    private List<RawRecipeDefinition> loadRawRecipeDefinitions() {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawRecipeDefinition>builder()
                        .clazz(RawRecipeDefinition.class)
                        .resourcePath(RECIPE_DEFINITION_LOCATION)
                        .schemaPath(RECIPE_SCHEMA_LOCATION)
                        .build()
        );
    }
}
