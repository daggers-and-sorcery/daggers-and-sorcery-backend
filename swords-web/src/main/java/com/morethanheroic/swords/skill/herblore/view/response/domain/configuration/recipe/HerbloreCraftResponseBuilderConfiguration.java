package com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.recipe;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.service.recipes.domain.HerbloreResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HerbloreCraftResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final HerbloreResult herbloreResult;
}
