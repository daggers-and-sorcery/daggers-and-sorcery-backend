package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.monster.service.MonsterDefinitionManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PositionResponseBuilder {

    private final MonsterDefinitionManager monsterDefinitionManager;
    private final MapManager mapManager;
    private final ResponseFactory responseFactory;

    @Autowired
    public PositionResponseBuilder(MonsterDefinitionManager monsterDefinitionManager, MapManager mapManager, ResponseFactory responseFactory) {
        this.monsterDefinitionManager = monsterDefinitionManager;
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

        spawnData.put("name", monsterDefinitionManager.getMonsterDefinition(spawn.getObject()).getName());
        spawnData.put("id", monsterDefinitionManager.getMonsterDefinition(spawn.getObject()).getId());

        return spawnData;
    }
}
