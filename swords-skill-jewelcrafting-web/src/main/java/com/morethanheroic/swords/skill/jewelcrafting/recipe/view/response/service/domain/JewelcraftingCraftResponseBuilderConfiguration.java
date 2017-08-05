package com.morethanheroic.swords.skill.jewelcrafting.recipe.view.response.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.jewelcrafting.service.recipe.domain.JewelcraftingResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JewelcraftingCraftResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final JewelcraftingResult jewelcraftingResult;
}
