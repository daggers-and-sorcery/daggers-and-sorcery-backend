package com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.recipe;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeInfoResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
}
