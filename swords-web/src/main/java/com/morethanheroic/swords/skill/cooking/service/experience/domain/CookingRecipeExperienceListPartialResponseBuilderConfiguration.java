package com.morethanheroic.swords.skill.cooking.service.experience.domain;

import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.swords.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipeExperienceListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final List<RecipeExperience> recipeExperiences;
}
