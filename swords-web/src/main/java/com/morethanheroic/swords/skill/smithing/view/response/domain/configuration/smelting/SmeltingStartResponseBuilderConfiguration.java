package com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.smelting;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.smithing.domain.SmeltingResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SmeltingStartResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final SmeltingResult smeltingResult;
}
