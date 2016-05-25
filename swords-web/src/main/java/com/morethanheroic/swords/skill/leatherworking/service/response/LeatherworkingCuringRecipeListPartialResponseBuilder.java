package com.morethanheroic.swords.skill.leatherworking.service.response;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.response.RecipePartialResponseBuilder;
import com.morethanheroic.swords.recipe.service.response.domain.RecipePartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.domain.configuration.RecipePartialResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeatherworkingCuringRecipeListPartialResponseBuilder implements PartialResponseCollectionBuilder<CookingInfoResponseBuilderConfiguration> {

    @Autowired
    @Qualifier("leatherworkingCuringLearnedRecipeEvaluator")
    private LearnedRecipeEvaluator learnedRecipeEvaluator;

    @Autowired
    private RecipePartialResponseBuilder recipePartialResponseBuilder;

    @Override
    public List<RecipePartialResponse> build(CookingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<RecipePartialResponse> result = new ArrayList<>();

        final List<RecipeDefinition> recipeEntities = learnedRecipeEvaluator.getLearnedRecipes(responseBuilderConfiguration.getUserEntity(), RecipeType.LEATHERWORKING_CURING);
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