package com.morethanheroic.swords.map.service;

import com.morethanheroic.swords.definition.service.XMLDefinitionLoader;
import com.morethanheroic.swords.map.service.domain.MapInfoDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class MapInfoDefinitionManager {

    @Autowired
    private XMLDefinitionLoader xmlDefinitionLoader;

    private HashMap<Integer, MapInfoDefinition> mapInfoDefinitionMap = new HashMap<>();

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() throws Exception {
        List<MapInfoDefinition> mapInfoDefinitionList = xmlDefinitionLoader.loadDefinitions(MapInfoDefinition.class, "classpath:data/map/definition/", "classpath:data/map/schema.xsd");

        for (MapInfoDefinition mapInfoDefinition : mapInfoDefinitionList) {
            mapInfoDefinitionMap.put(mapInfoDefinition.getId(), mapInfoDefinition);
        }
    }

    public MapInfoDefinition getMapInfoDefinition(int mapId) {
        return mapInfoDefinitionMap.get(mapId);
    }
}
