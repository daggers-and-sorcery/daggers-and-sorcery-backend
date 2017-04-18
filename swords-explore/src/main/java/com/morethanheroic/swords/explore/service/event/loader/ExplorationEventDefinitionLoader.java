package com.morethanheroic.swords.explore.service.event.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.domain.NumericDefinitionLoadingContext;
import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.loader.domain.RawEventDefinition;
import com.morethanheroic.swords.explore.service.event.transformer.ExplorationEventDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ExplorationEventDefinitionLoader implements DefinitionLoader<ExplorationEventDefinition> {

    private static final String EVENT_DEFINITION_LOCATION = "classpath:data/exploration/definition/";
    private static final String EVENT_SCHEMA_LOCATION = "classpath:data/exploration/schema.xsd";

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final ExplorationEventDefinitionTransformer explorationEventDefinitionTransformer;

    @Override
    public List<ExplorationEventDefinition> loadDefinitions() {
        return loadRawEventDefinitions().stream()
                .map(explorationEventDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }

    @SuppressWarnings("unchecked")
    private List<RawEventDefinition> loadRawEventDefinitions() {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawEventDefinition>builder()
                        .clazz(RawEventDefinition.class)
                        .resourcePath(EVENT_DEFINITION_LOCATION)
                        .schemaPath(EVENT_SCHEMA_LOCATION)
                        .build()
        );
    }
}
