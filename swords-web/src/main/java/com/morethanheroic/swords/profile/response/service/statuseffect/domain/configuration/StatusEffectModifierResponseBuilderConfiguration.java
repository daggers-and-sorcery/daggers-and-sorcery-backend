package com.morethanheroic.swords.profile.response.service.statuseffect.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.statuseffect.StatusEffectModifierPartialResponseBuilder;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectModifierDefinition;

import lombok.Builder;
import lombok.Getter;

/**
 * Configuration for {@link StatusEffectModifierPartialResponseBuilder}.
 */
@Getter
@Builder
public class StatusEffectModifierResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final StatusEffectModifierDefinition statusEffectModifierDefinition;
}
