package com.morethanheroic.swords.skill.cooking.service.response.experience.domain;

import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipeExperienceListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final List<RecipeExperience> recipeExperiences;
}
