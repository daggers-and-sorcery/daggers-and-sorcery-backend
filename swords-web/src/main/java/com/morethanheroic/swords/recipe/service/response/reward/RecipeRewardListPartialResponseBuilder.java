package com.morethanheroic.swords.recipe.service.response.reward;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeReward;
import com.morethanheroic.swords.recipe.service.response.reward.domain.RecipeRewardListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.reward.domain.RecipeRewardPartialResponse;
import com.morethanheroic.swords.recipe.service.response.reward.domain.RecipeRewardPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeRewardListPartialResponseBuilder
        implements PartialResponseCollectionBuilder<RecipeRewardListPartialResponseBuilderConfiguration> {

    private final RecipeRewardPartialResponseBuilder recipeRewardPartialResponseBuilder;

    @Override
    public List<RecipeRewardPartialResponse> build(RecipeRewardListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<RecipeRewardPartialResponse> result = new ArrayList<>();

        final List<RecipeReward> recipeRewards = responseBuilderConfiguration.getRecipeRewards();
        for (RecipeReward recipeReward : recipeRewards) {
            result.add(
                    recipeRewardPartialResponseBuilder.build(
                            RecipeRewardPartialResponseBuilderConfiguration.builder()
                                    .recipeReward(recipeReward)
                                    .build()
                    )
            );
        }

        return result;
    }
}
