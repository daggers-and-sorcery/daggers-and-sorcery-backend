package com.morethanheroic.swords.map.service;

import com.morethanheroic.swords.map.service.loader.MapLoader;
import com.swordssorcery.server.model.definition.map.MapDefinition;
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

    private HashMap<Integer, MapDefinition> mapHolder = new HashMap<>();

    public MapDefinition getMap(int id) {
        if (!mapHolder.containsKey(id)) {
            try {
                mapHolder.put(id, mapLoader.loadMapDefinition(id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return mapHolder.get(id);
    }
}
