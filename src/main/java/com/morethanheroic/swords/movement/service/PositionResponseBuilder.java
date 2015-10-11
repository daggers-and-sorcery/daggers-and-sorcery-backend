package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.monster.service.MonsterDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PositionResponseBuilder {

    private final MonsterDefinitionCache monsterDefinitionCache;
    private final MapManager mapManager;
    private final ResponseFactory responseFactory;

    @Autowired
    public PositionResponseBuilder(MonsterDefinitionCache monsterDefinitionCache, MapManager mapManager, ResponseFactory responseFactory) {
        this.monsterDefinitionCache = monsterDefinitionCache;
        this.mapManager = mapManager;
        this.responseFactory = responseFactory;
    }

    public Response build(UserEntity user) {
        Response response = responseFactory.newResponse(user);

        response.setData("x", user.getX());
        response.setData("y", user.getY());
        response.setData("map", user.getMapId());
        response.setData("spawnList", buildSpawnList(user));

        return response;
    }

    public List<HashMap<String, Object>> buildSpawnList(UserEntity user) {
        List<HashMap<String, Object>> userList = new ArrayList<>();

        for (MapObjectDatabaseEntity entity : mapManager.getMap(user.getMapId()).getSpawnsAt(user.getX(), user.getY())) {
            userList.add(buildSpawnData(entity));
        }

        return userList;
    }

    public HashMap<String, Object> buildSpawnData(MapObjectDatabaseEntity spawn) {
        HashMap<String, Object> spawnData = new HashMap<>();

        spawnData.put("name", monsterDefinitionCache.getMonsterDefinition(spawn.getObject()).getName());
        spawnData.put("id", monsterDefinitionCache.getMonsterDefinition(spawn.getObject()).getId());

        return spawnData;
    }
}
