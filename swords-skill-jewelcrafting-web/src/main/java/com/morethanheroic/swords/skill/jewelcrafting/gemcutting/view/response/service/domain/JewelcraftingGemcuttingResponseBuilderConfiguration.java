package com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.jewelcrafting.service.gemcutting.domain.GemcuttingResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JewelcraftingGemcuttingResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final GemcuttingResult gemcuttingResult;
}
