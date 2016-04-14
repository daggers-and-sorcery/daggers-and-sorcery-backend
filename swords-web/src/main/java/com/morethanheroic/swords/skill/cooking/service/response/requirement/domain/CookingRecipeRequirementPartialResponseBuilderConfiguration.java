package com.morethanheroic.swords.skill.cooking.service.response.requirement.domain;

import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeRequirementPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final RecipeRequirement recipeRequirement;
}
