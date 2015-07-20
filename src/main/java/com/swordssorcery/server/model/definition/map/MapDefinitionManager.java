package com.swordssorcery.server.model.definition.map;

import com.swordssorcery.server.model.definition.map.loader.MapLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class MapDefinitionManager {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MapLoader mapLoader;

    private HashMap<String, MapDefinition> mapHolder = new HashMap<>();

    public MapDefinition getMap(String name) throws IOException {
        if (!mapHolder.containsKey(name)) {
            mapHolder.put(name, mapLoader.loadMapDefinition(name));
        }

        return mapHolder.get(name);
    }
}
