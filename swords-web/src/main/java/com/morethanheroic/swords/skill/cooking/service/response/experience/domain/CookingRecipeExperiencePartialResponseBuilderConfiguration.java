package com.morethanheroic.swords.skill.cooking.service.response.experience.domain;

import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeExperiencePartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final RecipeExperience recipeExperience;
}
