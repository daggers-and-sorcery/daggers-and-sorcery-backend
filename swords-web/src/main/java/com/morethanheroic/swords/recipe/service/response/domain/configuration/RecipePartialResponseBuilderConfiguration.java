package com.morethanheroic.swords.recipe.service.response.domain.configuration;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipePartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final RecipeDefinition recipeDefinition;
}
