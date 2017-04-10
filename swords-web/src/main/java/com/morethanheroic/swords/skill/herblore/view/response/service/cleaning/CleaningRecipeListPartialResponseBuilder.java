package com.morethanheroic.swords.skill.herblore.view.response.service.cleaning;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.response.RecipePartialResponseBuilder;
import com.morethanheroic.swords.recipe.service.response.domain.configuration.RecipePartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.cleaning.CleaningInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CleaningRecipeListPartialResponseBuilder implements PartialResponseCollectionBuilder<CleaningInfoResponseBuilderConfiguration> {

    private final LearnedRecipeEvaluator learnedRecipeEvaluator;
    private final RecipePartialResponseBuilder recipePartialResponseBuilder;

    public CleaningRecipeListPartialResponseBuilder(@Qualifier("herbloreCleaningLearnedRecipeEvaluator") final LearnedRecipeEvaluator learnedRecipeEvaluator, final RecipePartialResponseBuilder recipePartialResponseBuilder) {
        this.learnedRecipeEvaluator = learnedRecipeEvaluator;
        this.recipePartialResponseBuilder = recipePartialResponseBuilder;
    }

    @Override
    public Collection<? extends PartialResponse> build(CleaningInfoResponseBuilderConfiguration cleaningInfoResponseBuilderConfiguration) {
        return listLearnedCleaningRecipes(cleaningInfoResponseBuilderConfiguration.getUserEntity()).stream()
                .map(recipeDefinition -> recipePartialResponseBuilder.build(
                        RecipePartialResponseBuilderConfiguration.builder()
                                .recipeDefinition(recipeDefinition)
                                .build()
                        )
                )
                .collect(Collectors.toList());
    }

    private List<RecipeDefinition> listLearnedCleaningRecipes(final UserEntity userEntity) {
        return learnedRecipeEvaluator.getLearnedRecipes(userEntity, RecipeType.HERBLORE_CLEANING);
    }
}
