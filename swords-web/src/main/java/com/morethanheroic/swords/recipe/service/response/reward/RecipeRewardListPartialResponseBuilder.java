package com.morethanheroic.swords.recipe.service.response.reward;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.service.response.reward.domain.RecipeRewardListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.reward.domain.RecipeRewardPartialResponse;
import com.morethanheroic.swords.recipe.service.response.reward.domain.RecipeRewardPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeRewardListPartialResponseBuilder
        implements PartialResponseCollectionBuilder<RecipeRewardListPartialResponseBuilderConfiguration> {

    private final RecipeRewardPartialResponseBuilder recipeRewardPartialResponseBuilder;

    @Override
    public List<RecipeRewardPartialResponse> build(RecipeRewardListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return responseBuilderConfiguration.getRecipeRewards().stream()
                .map(recipeReward -> recipeRewardPartialResponseBuilder.build(
                        RecipeRewardPartialResponseBuilderConfiguration.builder()
                                .recipeReward(recipeReward)
                                .build()
                        )
                )
                .collect(Collectors.toList());

    }
}
