package com.morethanheroic.swords.race.service.loader;

import com.morethanheroic.swords.definition.service.loader.XmlDefinitionLoader;
import com.morethanheroic.swords.race.model.RaceEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Load the {@link RaceEntity}es from xml files.
 */
public class RaceEntityLoader {

    @Autowired
    private XmlDefinitionLoader xmlDefinitionLoader;

    /**
     * TODO: write loading data, write transforming data.
     */
    public List<RaceEntity> loadRaceEntities() {
        return null;
    }
}
