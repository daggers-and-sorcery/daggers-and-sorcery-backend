package com.morethanheroic.swords.profile.response.service.statuseffect;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.profile.response.service.statuseffect.domain.configuration.StatusEffectModifierResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.statuseffect.domain.response.StatusEffectModifierPartialResponse;
import com.morethanheroic.swords.statuseffect.service.attribute.StatusEffectAttributeModifierCalculator;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.StatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectBasicModifierDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A {@link PartialResponseBuilder} for {@link StatusEffectBasicModifierDefinition}.
 */
@Service
@RequiredArgsConstructor
public class StatusEffectModifierPartialResponseBuilder implements PartialResponseCollectionBuilder<StatusEffectModifierResponseBuilderConfiguration> {

    private final StatusEffectAttributeModifierCalculator statusEffectAttributeModifierCalculator;

    @Override
    public List<PartialResponse> build(final StatusEffectModifierResponseBuilderConfiguration statusEffectModifierResponseBuilderConfiguration) {
        final List<StatusEffectAttributeModifierCalculationResult> statusEffectBasicModifierDefinitions = statusEffectAttributeModifierCalculator.calculate(statusEffectModifierResponseBuilderConfiguration.getUserEntity(), statusEffectModifierResponseBuilderConfiguration.getStatusEffectModifierDefinition(), null);

        return statusEffectBasicModifierDefinitions.stream()
                .map(statusEffectBasicModifierDefinition ->
                        StatusEffectModifierPartialResponse.builder()
                                .modifier(statusEffectBasicModifierDefinition.getModifier().getName())
                                .amount(statusEffectBasicModifierDefinition.getAmount())
                                .d2(statusEffectBasicModifierDefinition.getD2())
                                .d4(statusEffectBasicModifierDefinition.getD4())
                                .d6(statusEffectBasicModifierDefinition.getD6())
                                .d8(statusEffectBasicModifierDefinition.getD8())
                                .d10(statusEffectBasicModifierDefinition.getD10())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
