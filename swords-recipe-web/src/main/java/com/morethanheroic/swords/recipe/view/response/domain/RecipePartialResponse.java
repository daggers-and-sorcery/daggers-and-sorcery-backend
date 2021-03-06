package com.morethanheroic.swords.recipe.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.recipe.view.response.experience.domain.RecipeExperiencePartialResponse;
import com.morethanheroic.swords.recipe.view.response.ingredient.domain.RecipeIngredientPartialResponse;
import com.morethanheroic.swords.recipe.view.response.requirement.domain.RecipeRequirementPartialResponse;
import com.morethanheroic.swords.recipe.view.response.reward.domain.RecipeRewardPartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class RecipePartialResponse extends PartialResponse {

    private final int id;
    private final int chance;
    private final String name;
    private final List<RecipeIngredientPartialResponse> recipeIngredients;
    private final List<RecipeRewardPartialResponse> recipeRewards;
    private final List<RecipeExperiencePartialResponse> recipeExperiences;
    private final List<RecipeRequirementPartialResponse> recipeRequirements;
}
