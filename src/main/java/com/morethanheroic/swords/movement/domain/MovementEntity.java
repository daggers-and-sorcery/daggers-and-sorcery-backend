package com.morethanheroic.swords.movement.domain;

import com.morethanheroic.swords.map.domain.MapEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

public class MovementEntity {

    private final UserDatabaseEntity userDatabaseEntity;
    private final UserMapper userMapper;
    private final MapManager mapManager;

    public MovementEntity(UserDatabaseEntity userDatabaseEntity, UserMapper userMapper, MapManager mapManager) {
        this.userDatabaseEntity = userDatabaseEntity;
        this.userMapper = userMapper;
        this.mapManager = mapManager;
    }

    public void setPosition(int x, int y) {
        userDatabaseEntity.setX(x);
        userDatabaseEntity.setY(y);

        userMapper.updatePosition(userDatabaseEntity.getId(), x, y);
    }

    public void setMap(int mapId) {
        userDatabaseEntity.setMap(mapId);
    }

    public MapEntity getMap() {
        return mapManager.getMap(userDatabaseEntity.getMap());
    }

    public int getX() {
        return userDatabaseEntity.getX();
    }

    public int getY() {
        return userDatabaseEntity.getY();
    }
}
