package com.morethanheroic.swords.profile.response.service.statuseffect.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.statuseffect.StatusEffectPartialResponseCollectionBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.Builder;
import lombok.Getter;

/**
 * A configuration for the {@link StatusEffectPartialResponseCollectionBuilder}.
 */
@Getter
@Builder
public class StatusEffectResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
}
