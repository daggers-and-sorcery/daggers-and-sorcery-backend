package com.morethanheroic.swords.skill.cooking.service.response;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.DefaultLearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.response.RecipePartialResponseBuilder;
import com.morethanheroic.swords.recipe.service.response.domain.RecipePartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.domain.configuration.RecipePartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingRecipeListPartialResponseBuilder implements PartialResponseCollectionBuilder<CookingInfoResponseBuilderConfiguration> {

    private final DefaultLearnedRecipeEvaluator defaultLearnedRecipeEvaluator;
    private final RecipePartialResponseBuilder recipePartialResponseBuilder;

    @Override
    public List<RecipePartialResponse> build(CookingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<RecipePartialResponse> result = new ArrayList<>();

        //TODO: This informacion should come from the config! Not directly queried here.
        final List<RecipeDefinition> recipeEntities = defaultLearnedRecipeEvaluator.getLearnedRecipes(responseBuilderConfiguration.getUserEntity(), RecipeType.COOKING);
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
