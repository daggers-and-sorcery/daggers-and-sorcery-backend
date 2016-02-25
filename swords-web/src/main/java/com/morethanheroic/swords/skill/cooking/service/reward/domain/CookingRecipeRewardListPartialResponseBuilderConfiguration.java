package com.morethanheroic.swords.skill.cooking.service.reward.domain;

import com.morethanheroic.swords.recipe.domain.RecipeReward;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipeRewardListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final List<RecipeReward> recipeRewards;
}
