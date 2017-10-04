package com.morethanheroic.swords.event.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.domain.NumericDefinitionLoadingContext;
import com.morethanheroic.swords.event.domain.EventDefinition;
import com.morethanheroic.swords.event.service.loader.domain.RawEventDefinition;
import com.morethanheroic.swords.event.service.transformer.EventDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Loads {@link EventDefinition}s from the datapack.
 */
@Service
@RequiredArgsConstructor
public class EventDefinitionLoader implements DefinitionLoader<EventDefinition> {

    private static final String EVENT_DEFINITION_LOCATION = "classpath:data/event/definition/";
    private static final String EVENT_SCHEMA_LOCATION = "classpath:data/event/schema.xsd";

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final EventDefinitionTransformer eventDefinitionTransformer;

    @Override
    public List<EventDefinition> loadDefinitions() {
        return loadRawItemDefinitions().stream()
                .map(eventDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }

    private List<RawEventDefinition> loadRawItemDefinitions() {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawEventDefinition>builder()
                        .clazz(RawEventDefinition.class)
                        .resourcePath(EVENT_DEFINITION_LOCATION)
                        .schemaPath(EVENT_SCHEMA_LOCATION)
                        .build()
        );
    }
}
