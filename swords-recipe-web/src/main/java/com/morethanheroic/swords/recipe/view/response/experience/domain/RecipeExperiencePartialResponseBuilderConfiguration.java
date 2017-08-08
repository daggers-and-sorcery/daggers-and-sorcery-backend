package com.morethanheroic.swords.recipe.view.response.experience.domain;

import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeExperiencePartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final RecipeExperience recipeExperience;
}
