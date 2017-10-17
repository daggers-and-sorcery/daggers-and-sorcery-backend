package com.morethanheroic.swords.skill.tailoring.weaving.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.view.response.RecipePartialResponseBuilder;
import com.morethanheroic.swords.recipe.view.response.domain.configuration.RecipePartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.tailoring.weaving.view.response.service.domain.TailoringWeavingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TailoringWeavingRecipeListPartialResponseBuilder implements PartialResponseCollectionBuilder<TailoringWeavingInfoResponseBuilderConfiguration> {

    private final LearnedRecipeEvaluator learnedRecipeEvaluator;
    private final RecipePartialResponseBuilder recipePartialResponseBuilder;

    public TailoringWeavingRecipeListPartialResponseBuilder(@Qualifier("tailoringLearnedRecipeEvaluator") final LearnedRecipeEvaluator learnedRecipeEvaluator, final RecipePartialResponseBuilder recipePartialResponseBuilder) {
        this.learnedRecipeEvaluator = learnedRecipeEvaluator;
        this.recipePartialResponseBuilder = recipePartialResponseBuilder;
    }

    @Override
    public Collection<? extends PartialResponse> build(final TailoringWeavingInfoResponseBuilderConfiguration tailoringWeavingInfoResponseBuilderConfiguration) {
        return listLearnedWeavingRecipes(tailoringWeavingInfoResponseBuilderConfiguration.getUserEntity()).stream()
                .map(recipeDefinition -> recipePartialResponseBuilder.build(
                        RecipePartialResponseBuilderConfiguration.builder()
                                .userEntity(tailoringWeavingInfoResponseBuilderConfiguration.getUserEntity())
                                .recipeDefinition(recipeDefinition)
                                .userEntity(tailoringWeavingInfoResponseBuilderConfiguration.getUserEntity())
                                .build()
                        )
                )
                .collect(Collectors.toList());
    }

    private List<RecipeDefinition> listLearnedWeavingRecipes(final UserEntity userEntity) {
        return learnedRecipeEvaluator.getLearnedRecipes(userEntity, RecipeType.TAILORING_WEAVING);
    }
}
