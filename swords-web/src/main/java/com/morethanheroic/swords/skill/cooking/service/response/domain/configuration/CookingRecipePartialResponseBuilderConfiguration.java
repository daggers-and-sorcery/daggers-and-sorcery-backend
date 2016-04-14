package com.morethanheroic.swords.skill.cooking.service.response.domain.configuration;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipePartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final RecipeDefinition recipeDefinition;
}
