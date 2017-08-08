package com.morethanheroic.swords.recipe.view.response.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private UserEntity userEntity;
    private RecipeType recipeType;
}
