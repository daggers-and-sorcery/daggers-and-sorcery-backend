package com.morethanheroic.swords.recipe.service.response;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.learn.DefaultLearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.response.domain.RecipePartialResponse;
import com.morethanheroic.swords.recipe.service.response.domain.configuration.RecipeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.domain.configuration.RecipePartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeListPartialResponseBuilder implements PartialResponseCollectionBuilder<RecipeListPartialResponseBuilderConfiguration> {

    private final DefaultLearnedRecipeEvaluator defaultLearnedRecipeEvaluator;
    private final RecipePartialResponseBuilder recipePartialResponseBuilder;

    @Override
    public List<RecipePartialResponse> build(final RecipeListPartialResponseBuilderConfiguration recipeListPartialResponseBuilderConfiguration) {
        final List<RecipePartialResponse> result = new ArrayList<>();

        //TODO: This information should come from the config! Not directly queried here.
        final List<RecipeDefinition> recipeEntities = defaultLearnedRecipeEvaluator.getLearnedRecipes(recipeListPartialResponseBuilderConfiguration.getUserEntity(), recipeListPartialResponseBuilderConfiguration.getRecipeType());
        for (RecipeDefinition recipeDefinition : recipeEntities) {
            result.add(recipePartialResponseBuilder.build(
                    RecipePartialResponseBuilderConfiguration.builder()
                            .userEntity(recipeListPartialResponseBuilderConfiguration.getUserEntity())
                            .recipeDefinition(recipeDefinition)
                            .build()
            ));
        }

        return result;
    }
}