package com.morethanheroic.swords.effect.service.transformer;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.effect.service.domain.RawEffectDefinition;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Convert a {@link List} of {@link RawEffectDefinition} to a {@link List} of {@link EffectSettingDefinitionHolder}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EffectDefinitionListTransformer implements DefinitionListTransformer<List<EffectSettingDefinitionHolder>, List<RawEffectDefinition>> {

    @NonNull
    private final EffectDefinitionTransformer effectDefinitionTransformer;

    @Override
    public List<EffectSettingDefinitionHolder> transform(List<RawEffectDefinition> rawDefinition) {
        if (rawDefinition == null) {
            return Collections.emptyList();
        }

        return rawDefinition.stream().map(effectDefinitionTransformer::transform).collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }
}
