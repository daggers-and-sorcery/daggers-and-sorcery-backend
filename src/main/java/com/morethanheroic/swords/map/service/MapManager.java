package com.morethanheroic.swords.map.service;

import com.morethanheroic.swords.map.domain.MapEntity;
import com.morethanheroic.swords.map.service.domain.MapDefinition;
import com.morethanheroic.swords.map.service.loader.MapLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class MapManager {

    @Autowired
    private MapInfoDefinitionManager mapInfoDefinitionManager;

    @Autowired
    private MapLoader mapLoader;

    @Autowired
    private MapDatabaseManager mapDatabaseManager;

    private HashMap<Integer, MapEntity> mapEntityHashMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        List<MapDefinition> mapDefinitions = mapLoader.loadMapDefinitions();

        for (MapDefinition mapDefinition : mapDefinitions) {
            mapEntityHashMap.put(mapDefinition.getId(), buildMapEntity(mapDefinition));
        }
    }

    public MapEntity getMap(int id) {
        return mapEntityHashMap.get(id);
    }

    public MapEntity buildMapEntity(MapDefinition mapDefinition) {
        return new MapEntity(mapDefinition, mapInfoDefinitionManager.getMapInfoDefinition(mapDefinition.getId()), mapDatabaseManager.getMapDatabaseEntity(mapDefinition.getId()));
    }

    public List<MapEntity> getMapList() {
        return Collections.unmodifiableList(new ArrayList<>(mapEntityHashMap.values()));
    }
}
