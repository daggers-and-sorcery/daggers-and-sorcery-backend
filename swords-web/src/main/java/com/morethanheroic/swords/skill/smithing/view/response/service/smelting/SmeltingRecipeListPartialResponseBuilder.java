package com.morethanheroic.swords.skill.smithing.view.response.service.smelting;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.view.response.RecipePartialResponseBuilder;
import com.morethanheroic.swords.recipe.view.response.domain.RecipePartialResponse;
import com.morethanheroic.swords.recipe.view.response.domain.configuration.RecipePartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.smelting.SmeltingInfoResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmeltingRecipeListPartialResponseBuilder implements PartialResponseCollectionBuilder<SmeltingInfoResponseBuilderConfiguration> {

    @Autowired
    @Qualifier("smithingSmeltingLearnedRecipeEvaluator")
    private LearnedRecipeEvaluator learnedRecipeEvaluator;

    @Autowired
    private RecipePartialResponseBuilder recipePartialResponseBuilder;

    @Override
    public List<RecipePartialResponse> build(final SmeltingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<RecipeDefinition> recipeEntities = learnedRecipeEvaluator.getLearnedRecipes(responseBuilderConfiguration.getUserEntity(), RecipeType.SMITHING_SMELTING);

        return recipeEntities.stream()
                .map(recipeDefinition -> recipePartialResponseBuilder.build(
                        RecipePartialResponseBuilderConfiguration.builder()
                                .userEntity(responseBuilderConfiguration.getUserEntity())
                                .recipeDefinition(recipeDefinition)
                                .build()
                        )
                )
                .collect(Collectors.toList());
    }
}
