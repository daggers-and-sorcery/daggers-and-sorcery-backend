package com.swordssorcery.server.game.map;

import com.swordssorcery.server.model.db.map.MapRepository;
import com.swordssorcery.server.model.entity.map.MapEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MapManager {

    @Autowired
    private MapDefinitionManager mapDefinitionManager;

    @Autowired
    private MapRepository mapRepository;

    private HashMap<Integer, MapEntity> mapEntityHashMap = new HashMap<>();

    public MapEntity getMap(int id) {
        if (!mapEntityHashMap.containsKey(id)) {
            mapEntityHashMap.put(id, buildMapEntity(id));
        }

        return mapEntityHashMap.get(id);
    }

    public MapEntity buildMapEntity(int id) {
        return new MapEntity(id, mapDefinitionManager.getMap(id), mapRepository.findOne(id));
    }

    public void saveMapEntity(MapEntity mapEntity) {
        //TODO save
    }
}
