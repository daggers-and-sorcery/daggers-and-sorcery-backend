package com.morethanheroic.swords.skill.cooking.service.response.requirement.domain;

import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipeRequirementListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final List<RecipeRequirement>  recipeRequirements;
}
