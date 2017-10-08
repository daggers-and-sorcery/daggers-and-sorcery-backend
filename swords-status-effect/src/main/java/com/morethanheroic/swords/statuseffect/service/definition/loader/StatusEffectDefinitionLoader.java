package com.morethanheroic.swords.statuseffect.service.definition.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.loader.domain.RawStatusEffectDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.transformer.StatusEffectDefinitionTransformer;
import com.morethanheroic.xml.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.xml.service.loader.domain.NumericDefinitionLoadingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Load the {@link com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectDefinition}s.
 */
@Service
@RequiredArgsConstructor
public class StatusEffectDefinitionLoader implements DefinitionLoader<StatusEffectDefinition> {

    private static final String STATUS_EFFECT_DEFINITION_LOCATION = "classpath:data/status-effect/definition/";
    private static final String STATUS_EFFECT_SCHEMA_LOCATION = "classpath:data/status-effect/schema.xsd";

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final StatusEffectDefinitionTransformer statusEffectDefinitionTransformer;

    @Override
    public List<StatusEffectDefinition> loadDefinitions() {
        return loadRawStatusEffectDefinitions().stream()
                .map(statusEffectDefinitionTransformer::transform)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private List<RawStatusEffectDefinition> loadRawStatusEffectDefinitions() {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawStatusEffectDefinition>builder()
                        .clazz(RawStatusEffectDefinition.class)
                        .resourcePath(STATUS_EFFECT_DEFINITION_LOCATION)
                        .schemaPath(STATUS_EFFECT_SCHEMA_LOCATION)
                        .build()
        );
    }
}
