package com.morethanheroic.swords.statuseffect.service.definition.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.effect.service.transformer.EffectDefinitionListTransformer;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.loader.domain.RawStatusEffectDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A transformer to transform {@link RawStatusEffectDefinition}s to {@link StatusEffectDefinition}.
 */
@Service
@RequiredArgsConstructor
public class StatusEffectDefinitionTransformer implements DefinitionTransformer<StatusEffectDefinition, RawStatusEffectDefinition> {

    private final EffectDefinitionListTransformer effectDefinitionListTransformer;

    @Override
    @SuppressWarnings("unchecked")
    public StatusEffectDefinition transform(final RawStatusEffectDefinition rawDefinition) {
        return StatusEffectDefinition.builder()
                .id(rawDefinition.getId())
                .name(rawDefinition.getName())
                .description(rawDefinition.getDescription())
                .combatEffects(effectDefinitionListTransformer.transform((List) rawDefinition.getEffectList()))
                .build();
    }
}
