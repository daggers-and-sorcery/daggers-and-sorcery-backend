package com.morethanheroic.swords.map.service;

import com.morethanheroic.swords.map.repository.dao.MapObjectMapper;
import com.morethanheroic.swords.map.repository.dao.MapMapper;
import com.morethanheroic.swords.map.repository.domain.MapDatabaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.HashMap;

@Service
public class MapDatabaseManager {

    @Autowired
    private MapMapper mapRepository;

    @Autowired
    private MapObjectMapper mapObjectMapper;

    private HashMap<Integer, MapDatabaseEntity> mapDatabaseEntityHashMap = new HashMap<>();

    @PreDestroy
    public void destroy() {
        for(MapDatabaseEntity mapDatabaseEntity : mapDatabaseEntityHashMap.values()) {
            try {
                //System.out.println(mapDatabaseEntity.getSpawns().size());
                /*for (MapObectDatabaseEntity ent : mapDatabaseEntity.getSpawns()) {
                    System.out.println("x" + ent.getX() + " y: " + ent.getY());
                    System.out.println("id:" + ent.getMap().getId());
                    mapObjectRepository.save(ent);
                }*/

                mapRepository.update(mapDatabaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public MapDatabaseEntity getMapDatabaseEntity(int mapId) {
        if(!mapDatabaseEntityHashMap.containsKey(mapId)) {
            mapDatabaseEntityHashMap.put(mapId, mapRepository.getById(mapId));
        }

        return mapDatabaseEntityHashMap.get(mapId);
    }
}
