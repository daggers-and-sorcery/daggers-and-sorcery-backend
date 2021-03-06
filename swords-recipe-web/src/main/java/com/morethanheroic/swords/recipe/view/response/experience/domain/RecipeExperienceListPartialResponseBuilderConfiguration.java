package com.morethanheroic.swords.recipe.view.response.experience.domain;

import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class RecipeExperienceListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final List<RecipeExperience> recipeExperiences;
}
