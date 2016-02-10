package com.morethanheroic.swords.skill.cooking.service.reward;

import com.morethanheroic.swords.recipe.domain.RecipeReward;
import com.morethanheroic.swords.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.reward.domain.CookingRecipeRewardPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.reward.domain.CookingRecipeRewardPartialResponseBuilderConfiguration;

public class CookingRecipeRewardPartialResponseBuilder implements PartialResponseBuilder<CookingRecipeRewardPartialResponseBuilderConfiguration> {

    @Override
    public CookingRecipeRewardPartialResponse build(CookingRecipeRewardPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeReward recipeReward = responseBuilderConfiguration.getRecipeReward();

        return CookingRecipeRewardPartialResponse.builder()
                .id(recipeReward.getId())
                .amount(recipeReward.getAmount())
                .build();
    }
}
