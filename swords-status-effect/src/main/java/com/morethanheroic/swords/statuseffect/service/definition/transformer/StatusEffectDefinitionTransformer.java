package com.morethanheroic.swords.statuseffect.service.definition.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.effect.service.transformer.EffectDefinitionListTransformer;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectModifierDefinition;
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
            .map(rawStatusEffectModifierDefinition -> StatusEffectModifierDefinition.builder()
                .modifier(rawStatusEffectModifierDefinition.getModifier())
                .amount(rawStatusEffectModifierDefinition.getAmount())
                .d2(rawStatusEffectModifierDefinition.getD2())
                .d4(rawStatusEffectModifierDefinition.getD4())
                .d6(rawStatusEffectModifierDefinition.getD6())
                .d8(rawStatusEffectModifierDefinition.getD8())
                .d10(rawStatusEffectModifierDefinition.getD10())
                .build())
            .collect(Collectors.toList());
    }
}
