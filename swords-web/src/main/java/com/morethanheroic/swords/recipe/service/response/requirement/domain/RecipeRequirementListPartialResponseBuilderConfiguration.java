package com.morethanheroic.swords.recipe.service.response.requirement.domain;

import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class RecipeRequirementListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final List<RecipeRequirement>  recipeRequirements;
}
