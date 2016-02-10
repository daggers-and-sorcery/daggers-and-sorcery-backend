package com.morethanheroic.swords.skill.cooking.service.reward.domain;

import com.morethanheroic.swords.recipe.domain.RecipeReward;
import com.morethanheroic.swords.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeRewardPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final RecipeReward recipeReward;
}
