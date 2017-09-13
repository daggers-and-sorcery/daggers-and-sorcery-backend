package com.morethanheroic.swords.skill.crafting.view.response.service.recipe.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CraftingRecipeInfoResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
}
