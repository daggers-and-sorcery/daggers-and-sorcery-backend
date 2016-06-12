package com.morethanheroic.swords.recipe.service.response;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.DefaultLearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.response.domain.RecipePartialResponse;
import com.morethanheroic.swords.recipe.service.response.domain.configuration.RecipeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.domain.configuration.RecipePartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.working.WorkingInfoResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeListPartialResponseBuilder implements PartialResponseCollectionBuilder<RecipeListPartialResponseBuilderConfiguration> {

    private final DefaultLearnedRecipeEvaluator defaultLearnedRecipeEvaluator;
    private final RecipePartialResponseBuilder recipePartialResponseBuilder;

    @Override
    public List<RecipePartialResponse> build(RecipeListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<RecipePartialResponse> result = new ArrayList<>();

        //TODO: This information should come from the config! Not directly queried here.
        final List<RecipeDefinition> recipeEntities = defaultLearnedRecipeEvaluator.getLearnedRecipes(responseBuilderConfiguration.getUserEntity(), responseBuilderConfiguration.getRecipeType());
        for (RecipeDefinition recipeDefinition : recipeEntities) {
            result.add(recipePartialResponseBuilder.build(
                    RecipePartialResponseBuilderConfiguration.builder()
                            .recipeDefinition(recipeDefinition)
                            .build()
            ));
        }

        return result;
    }
}