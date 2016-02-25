package com.morethanheroic.swords.skill.cooking.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.cooking.service.experience.domain.CookingRecipeExperiencePartialResponse;
import com.morethanheroic.swords.skill.cooking.service.ingredient.domain.CookingRecipeIngredientPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.requirement.domain.CookingRecipeRequirementPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.reward.domain.CookingRecipeRewardPartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipePartialResponse extends PartialResponse {

    private final int id;
    private final int chance;
    private final String name;
    private final List<CookingRecipeIngredientPartialResponse> recipeIngredients;
    private final List<CookingRecipeRewardPartialResponse> recipeRewards;
    private final List<CookingRecipeExperiencePartialResponse> recipeExperiences;
    private final List<CookingRecipeRequirementPartialResponse> recipeRequirements;
}
