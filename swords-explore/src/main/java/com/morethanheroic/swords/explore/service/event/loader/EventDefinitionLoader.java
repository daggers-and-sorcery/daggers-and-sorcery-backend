package com.morethanheroic.swords.explore.service.event.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.loader.domain.RawEventDefinition;
import com.morethanheroic.swords.explore.service.event.transformer.ExplorationEventDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EventDefinitionLoader implements DefinitionLoader<ExplorationEventDefinition> {

    private static final String EVENT_DEFINITION_LOCATION = "classpath:data/event/definition/";
    private static final String EVENT_SCHEMA_LOCATION = "classpath:data/event/schema.xsd";
    private static final int EVENT_COUNT_TO_LOAD = 50;

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final ExplorationEventDefinitionTransformer explorationEventDefinitionTransformer;

    @Override
    public List<ExplorationEventDefinition> loadDefinitions() throws IOException {
        return loadRawEventDefinitions().stream().map(explorationEventDefinitionTransformer::transform).collect(
                collectingAndThen(toList(), ImmutableList::copyOf)
        );
    }

    @SuppressWarnings("unchecked")
    private List<RawEventDefinition> loadRawEventDefinitions() throws IOException {
        return numericXmlDefinitionLoader.loadDefinitions(RawEventDefinition.class, EVENT_DEFINITION_LOCATION,
                EVENT_SCHEMA_LOCATION, EVENT_COUNT_TO_LOAD);
    }
}
