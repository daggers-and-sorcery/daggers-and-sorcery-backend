package com.morethanheroic.swords.profile.response.service.statuseffect.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.statuseffect.StatusEffectModifierPartialResponseBuilder;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectModifierDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

/**
 * Configuration for {@link StatusEffectModifierPartialResponseBuilder}.
 */
@Getter
@Builder
public class StatusEffectModifierResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final StatusEffectModifierDefinition statusEffectModifierDefinition;
}
