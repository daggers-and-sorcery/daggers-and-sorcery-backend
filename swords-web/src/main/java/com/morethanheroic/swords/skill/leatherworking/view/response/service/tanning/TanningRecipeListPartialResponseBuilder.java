package com.morethanheroic.swords.skill.leatherworking.view.response.service.tanning;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.view.response.RecipePartialResponseBuilder;
import com.morethanheroic.swords.recipe.view.response.domain.RecipePartialResponse;
import com.morethanheroic.swords.recipe.view.response.domain.configuration.RecipePartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.tanning.TanningInfoResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TanningRecipeListPartialResponseBuilder implements PartialResponseCollectionBuilder<TanningInfoResponseBuilderConfiguration> {

    @Autowired
    @Qualifier("leatherworkingTanningLearnedRecipeEvaluator")
    private LearnedRecipeEvaluator learnedRecipeEvaluator;

    @Autowired
    private RecipePartialResponseBuilder recipePartialResponseBuilder;

    @Override
    public List<RecipePartialResponse> build(TanningInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<RecipePartialResponse> result = new ArrayList<>();

        final List<RecipeDefinition> recipeEntities = learnedRecipeEvaluator.getLearnedRecipes(responseBuilderConfiguration.getUserEntity(), RecipeType.LEATHERWORKING_TANNING);
        for (RecipeDefinition recipeDefinition : recipeEntities) {
            result.add(recipePartialResponseBuilder.build(
                    RecipePartialResponseBuilderConfiguration.builder()
                            .userEntity(responseBuilderConfiguration.getUserEntity())
                            .recipeDefinition(recipeDefinition)
                            .build()
            ));
        }

        return result;
    }
}
