package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.movement.domain.MovementEntity;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionResponseBuilder {

    private final MonsterDefinitionCache monsterDefinitionCache;
    private final MapManager mapManager;
    private final ResponseFactory responseFactory;

    @Autowired
    private MovementFacade movementFacade;

    @Autowired
    public PositionResponseBuilder(MonsterDefinitionCache monsterDefinitionCache, MapManager mapManager, ResponseFactory responseFactory) {
        this.monsterDefinitionCache = monsterDefinitionCache;
        this.mapManager = mapManager;
        this.responseFactory = responseFactory;
    }

    public CharacterRefreshResponse build(UserEntity userEntity) {
        CharacterRefreshResponse response = responseFactory.newResponse(userEntity);

        MovementEntity movementEntity = movementFacade.getEntity(userEntity);

        response.setData("x", movementEntity.getX());
        response.setData("y", movementEntity.getY());
        response.setData("map", movementEntity.getMap().getId());
        response.setData("spawnList", buildSpawnList(movementEntity));

        return response;
    }

    public List<HashMap<String, Object>> buildSpawnList(MovementEntity movementEntity) {
        return mapManager.getMap(movementEntity.getMap().getId()).getSpawnsAt(movementEntity.getX(), movementEntity.getY()).stream().map(this::buildSpawnData).collect(Collectors.toList());
    }

    public HashMap<String, Object> buildSpawnData(MapObjectDatabaseEntity spawn) {
        HashMap<String, Object> spawnData = new HashMap<>();

        spawnData.put("name", monsterDefinitionCache.getMonsterDefinition(spawn.getObject()).getName());
        spawnData.put("id", monsterDefinitionCache.getMonsterDefinition(spawn.getObject()).getId());

        return spawnData;
    }
}
