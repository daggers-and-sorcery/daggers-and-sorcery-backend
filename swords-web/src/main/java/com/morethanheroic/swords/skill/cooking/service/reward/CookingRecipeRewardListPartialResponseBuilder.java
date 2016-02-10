package com.morethanheroic.swords.skill.cooking.service.reward;

import com.morethanheroic.swords.recipe.domain.RecipeReward;
import com.morethanheroic.swords.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.skill.cooking.service.reward.domain.CookingRecipeRewardListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.reward.domain.CookingRecipeRewardPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.reward.domain.CookingRecipeRewardPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingRecipeRewardListPartialResponseBuilder
        implements PartialResponseCollectionBuilder<CookingRecipeRewardListPartialResponseBuilderConfiguration> {

    private final CookingRecipeRewardPartialResponseBuilder cookingRecipeRewardPartialResponseBuilder;

    @Override
    public List<CookingRecipeRewardPartialResponse> build(CookingRecipeRewardListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<CookingRecipeRewardPartialResponse> result = new ArrayList<>();

        final List<RecipeReward> recipeRewards = responseBuilderConfiguration.getRecipeRewards();
        for (RecipeReward recipeReward : recipeRewards) {
            result.add(
                    cookingRecipeRewardPartialResponseBuilder.build(
                            CookingRecipeRewardPartialResponseBuilderConfiguration.builder()
                                    .recipeReward(recipeReward)
                                    .build()
                    )
            );
        }

        return result;
    }
}
