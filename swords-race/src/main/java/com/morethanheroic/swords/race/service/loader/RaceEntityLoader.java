package com.morethanheroic.swords.race.service.loader;

import com.morethanheroic.swords.definition.service.loader.EnumXmlDefinitionLoader;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceEntity;
import com.morethanheroic.swords.race.service.loader.entity.RawRaceEntity;
import com.morethanheroic.swords.race.service.transformer.RaceEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Load the {@link RaceEntity}es from xml files.
 */
@Service
public class RaceEntityLoader {

    @Autowired
    private EnumXmlDefinitionLoader enumXmlDefinitionLoader;

    @Autowired
    private RaceEntityTransformer raceEntityTransformer;

    public List<RaceEntity> loadRaceEntities() throws IOException {
        return loadRawRaceEntities().stream().map(raceEntityTransformer::transform).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public List<RawRaceEntity> loadRawRaceEntities() throws IOException {
        return enumXmlDefinitionLoader.loadDefinitions(RawRaceEntity.class, "classpath:data/race/definition/", "classpath:data/race/schema.xsd", Race.class);
    }
}
