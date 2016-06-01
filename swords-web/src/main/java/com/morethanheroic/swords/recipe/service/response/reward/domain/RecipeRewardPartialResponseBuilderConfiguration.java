package com.morethanheroic.swords.recipe.service.response.reward.domain;

import com.morethanheroic.swords.recipe.domain.RecipeReward;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeRewardPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final RecipeReward recipeReward;
}
