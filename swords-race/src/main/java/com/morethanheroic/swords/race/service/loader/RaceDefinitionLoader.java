package com.morethanheroic.swords.race.service.loader;

import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.EnumXmlDefinitionLoader;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceDefinition;
import com.morethanheroic.swords.race.service.loader.entity.RawRaceDefinition;
import com.morethanheroic.swords.race.service.transformer.RaceDefinitionTransformer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Load the {@link RaceDefinition}es from xml files.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RaceDefinitionLoader implements DefinitionLoader<RaceDefinition> {

    @NonNull
    private EnumXmlDefinitionLoader enumXmlDefinitionLoader;

    @NonNull
    private RaceDefinitionTransformer raceDefinitionTransformer;

    @Override
    public List<RaceDefinition> loadDefinitions() throws IOException {
        return loadRawRaceEntities().stream().map(raceDefinitionTransformer::transform).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public List<RawRaceDefinition> loadRawRaceEntities() throws IOException {
        return enumXmlDefinitionLoader.loadDefinitions(RawRaceDefinition.class, "classpath:data/race/definition/", "classpath:data/race/schema.xsd",
                Race.class);
    }
}
