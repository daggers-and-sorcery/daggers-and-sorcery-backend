package com.morethanheroic.swords.statuseffect.service.definition.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectBasicModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectCustomModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.loader.domain.RawStatusEffectBasicModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.loader.domain.RawStatusEffectCustomModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.loader.domain.RawStatusEffectDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.loader.domain.RawStatusEffectModifierDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A transformer to transform {@link RawStatusEffectDefinition}s to {@link StatusEffectDefinition}.
 */
@Service
@RequiredArgsConstructor
public class StatusEffectDefinitionTransformer implements DefinitionTransformer<StatusEffectDefinition, RawStatusEffectDefinition> {

    @Override
    @SuppressWarnings("unchecked")
    public StatusEffectDefinition transform(final RawStatusEffectDefinition rawDefinition) {
        return StatusEffectDefinition.builder()
                .id(rawDefinition.getId())
                .name(rawDefinition.getName())
                .description(rawDefinition.getDescription())
                .modifiers(transformStatusEffectModifiers(rawDefinition.getModifiers()))
                .build();
    }

    private List<StatusEffectModifierDefinition> transformStatusEffectModifiers(final List<RawStatusEffectModifierDefinition> statusEffectModifierDefinitions) {
        return statusEffectModifierDefinitions.stream()
                .map(rawStatusEffectModifierDefinition -> transformModifierDefinition(rawStatusEffectModifierDefinition))
                .collect(Collectors.toList());
    }

    private StatusEffectModifierDefinition transformModifierDefinition(final RawStatusEffectModifierDefinition rawStatusEffectModifierDefinition) {
        if (rawStatusEffectModifierDefinition instanceof RawStatusEffectBasicModifierDefinition) {
            final RawStatusEffectBasicModifierDefinition rawStatusEffectBasicModifierDefinition = (RawStatusEffectBasicModifierDefinition) rawStatusEffectModifierDefinition;

            return StatusEffectBasicModifierDefinition.builder()
                    .modifier(rawStatusEffectBasicModifierDefinition.getModifier())
                    .amount(rawStatusEffectBasicModifierDefinition.getAmount())
                    .d2(rawStatusEffectBasicModifierDefinition.getD2())
                    .d4(rawStatusEffectBasicModifierDefinition.getD4())
                    .d6(rawStatusEffectBasicModifierDefinition.getD6())
                    .d8(rawStatusEffectBasicModifierDefinition.getD8())
                    .d10(rawStatusEffectBasicModifierDefinition.getD10())
                    .build();
        } else {
            final RawStatusEffectCustomModifierDefinition rawStatusEffectCustomModifierDefinition = (RawStatusEffectCustomModifierDefinition) rawStatusEffectModifierDefinition;

            return StatusEffectCustomModifierDefinition.builder()
                    .effectId(rawStatusEffectCustomModifierDefinition.getEffectId())
                    .build();
        }
    }
}
