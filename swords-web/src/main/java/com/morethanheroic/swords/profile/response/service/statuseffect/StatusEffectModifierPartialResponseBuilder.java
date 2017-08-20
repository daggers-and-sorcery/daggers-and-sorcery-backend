package com.morethanheroic.swords.profile.response.service.statuseffect;

import org.springframework.stereotype.Service;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.profile.response.service.statuseffect.domain.configuration.StatusEffectModifierResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.statuseffect.domain.response.StatusEffectModifierPartialResponse;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectBasicModifierDefinition;

/**
 * A {@link PartialResponseBuilder} for {@link StatusEffectBasicModifierDefinition}.
 */
@Service
public class StatusEffectModifierPartialResponseBuilder implements PartialResponseBuilder<StatusEffectModifierResponseBuilderConfiguration> {

    @Override
    public StatusEffectModifierPartialResponse build(final StatusEffectModifierResponseBuilderConfiguration statusEffectModifierResponseBuilderConfiguration) {
        final StatusEffectBasicModifierDefinition statusEffectModifierDefinition = statusEffectModifierResponseBuilderConfiguration.getStatusEffectModifierDefinition();

        return StatusEffectModifierPartialResponse.builder()
            .modifier(statusEffectModifierDefinition.getModifier().getName())
            .amount(statusEffectModifierDefinition.getAmount())
            .d2(statusEffectModifierDefinition.getD2())
            .d4(statusEffectModifierDefinition.getD4())
            .d6(statusEffectModifierDefinition.getD6())
            .d8(statusEffectModifierDefinition.getD8())
            .d10(statusEffectModifierDefinition.getD10())
            .build();
    }
}
