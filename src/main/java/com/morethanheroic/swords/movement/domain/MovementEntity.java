package com.morethanheroic.swords.movement.domain;

import com.morethanheroic.swords.map.domain.MapEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.movement.repository.dao.MovementDatabaseEntity;
import com.morethanheroic.swords.movement.repository.domain.MovementMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

public class MovementEntity {

    private final MovementDatabaseEntity movementDatabaseEntity;
    private final MovementMapper movementMapper;
    private final MapManager mapManager;

    public MovementEntity(MovementDatabaseEntity movementDatabaseEntity, MovementMapper movementMapper, MapManager mapManager) {
        this.movementDatabaseEntity = movementDatabaseEntity;
        this.movementMapper = movementMapper;
        this.mapManager = mapManager;
    }

    public void setPosition(int x, int y) {
        movementDatabaseEntity.setX(x);
        movementDatabaseEntity.setY(y);

        movementMapper.updatePosition(movementDatabaseEntity.getUserId(), x, y);
    }

    public void setMap(int mapId) {
        movementDatabaseEntity.setMapId(mapId);
    }

    public MapEntity getMap() {
        return mapManager.getMap(movementDatabaseEntity.getMapId());
    }

    public int getX() {
        return movementDatabaseEntity.getX();
    }

    public int getY() {
        return movementDatabaseEntity.getY();
    }
}
