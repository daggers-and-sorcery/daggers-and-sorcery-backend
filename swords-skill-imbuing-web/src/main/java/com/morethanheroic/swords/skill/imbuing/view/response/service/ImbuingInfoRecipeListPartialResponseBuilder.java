package com.morethanheroic.swords.skill.imbuing.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.view.response.RecipePartialResponseBuilder;
import com.morethanheroic.swords.recipe.view.response.domain.configuration.RecipePartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.imbuing.view.response.service.domain.ImbuingInfoResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImbuingInfoRecipeListPartialResponseBuilder implements PartialResponseCollectionBuilder<ImbuingInfoResponseBuilderConfiguration> {

    @Autowired
    @Qualifier("imbuingLearnedRecipeEvaluator")
    private LearnedRecipeEvaluator learnedRecipeEvaluator;

    @Autowired
    private RecipePartialResponseBuilder recipePartialResponseBuilder;

    @Override
    public Collection<? extends PartialResponse> build(ImbuingInfoResponseBuilderConfiguration imbuingInfoResponseBuilderConfiguration) {
        final List<RecipeDefinition> recipeEntities = learnedRecipeEvaluator.getLearnedRecipes(imbuingInfoResponseBuilderConfiguration.getUserEntity(), RecipeType.IMBUING);

        return recipeEntities.stream()
                .map(recipeDefinition -> recipePartialResponseBuilder.build(
                        RecipePartialResponseBuilderConfiguration.builder()
                                .userEntity(imbuingInfoResponseBuilderConfiguration.getUserEntity())
                                .recipeDefinition(recipeDefinition)
                                .build()
                        )
                )
                .collect(Collectors.toList());
    }
}
