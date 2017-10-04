package com.morethanheroic.swords.race.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.EnumXmlDefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.domain.EnumDefinitionLoadingContext;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceDefinition;
import com.morethanheroic.swords.race.service.loader.entity.RawRaceDefinition;
import com.morethanheroic.swords.race.service.transformer.RaceDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Load the {@link RaceDefinition}es from xml files.
 */
@Service
@RequiredArgsConstructor
public class RaceDefinitionLoader implements DefinitionLoader<RaceDefinition> {

    private final EnumXmlDefinitionLoader enumXmlDefinitionLoader;
    private final RaceDefinitionTransformer raceDefinitionTransformer;

    @Override
    public List<RaceDefinition> loadDefinitions() {
        return loadRawRaceEntities().stream()
                .map(raceDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }

    private List<RawRaceDefinition> loadRawRaceEntities() {
        return enumXmlDefinitionLoader.loadDefinitions(
                EnumDefinitionLoadingContext.<RawRaceDefinition>builder()
                        .clazz(RawRaceDefinition.class)
                        .resourcePath("classpath:data/race/definition/")
                        .schemaPath("classpath:data/race/schema.xsd")
                        .target(Race.class)
                        .build()
        );
    }
}
