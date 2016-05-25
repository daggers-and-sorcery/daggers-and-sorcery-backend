package com.morethanheroic.swords.skill.leatherworking.service.response;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.response.CookingRecipePartialResponseBuilder;
import com.morethanheroic.swords.recipe.service.response.domain.CookingRecipePartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.domain.configuration.CookingRecipePartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
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
    private CookingRecipePartialResponseBuilder cookingRecipePartialResponseBuilder;

    @Override
    public List<CookingRecipePartialResponse> build(CookingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<CookingRecipePartialResponse> result = new ArrayList<>();

        final List<RecipeDefinition> recipeEntities = learnedRecipeEvaluator.getLearnedRecipes(responseBuilderConfiguration.getUserEntity(), RecipeType.LEATHERWORKING_CURING);
        for (RecipeDefinition recipeDefinition : recipeEntities) {
            result.add(cookingRecipePartialResponseBuilder.build(
                    CookingRecipePartialResponseBuilderConfiguration.builder()
                            .recipeDefinition(recipeDefinition)
                            .build()
            ));
        }

        return result;
    }
}