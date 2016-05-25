package com.morethanheroic.swords.skill.cooking.service.response;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.DefaultLearnedRecipeEvaluator;
import com.morethanheroic.swords.skill.cooking.service.response.domain.CookingRecipePartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingRecipePartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingRecipeListPartialResponseBuilder implements PartialResponseCollectionBuilder<CookingInfoResponseBuilderConfiguration> {

    private final DefaultLearnedRecipeEvaluator defaultLearnedRecipeEvaluator;
    private final CookingRecipePartialResponseBuilder cookingRecipePartialResponseBuilder;

    @Override
    public List<CookingRecipePartialResponse> build(CookingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<CookingRecipePartialResponse> result = new ArrayList<>();

        final List<RecipeDefinition> recipeEntities = defaultLearnedRecipeEvaluator.getLearnedRecipes(responseBuilderConfiguration.getUserEntity(), RecipeType.COOKING);
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
