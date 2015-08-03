package com.morethanheroic.swords.map.service;

import com.morethanheroic.swords.map.repository.dao.MapMapper;
import com.morethanheroic.swords.map.repository.domain.MapDatabaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MapDatabaseManager {

    @Autowired
    private MapMapper mapRepository;

    private HashMap<Integer, MapDatabaseEntity> mapDatabaseEntityHashMap = new HashMap<>();

    public MapDatabaseEntity getMapDatabaseEntity(int mapId) {
        if (!mapDatabaseEntityHashMap.containsKey(mapId)) {
            mapDatabaseEntityHashMap.put(mapId, mapRepository.getById(mapId));
        }

        return mapDatabaseEntityHashMap.get(mapId);
    }
}
