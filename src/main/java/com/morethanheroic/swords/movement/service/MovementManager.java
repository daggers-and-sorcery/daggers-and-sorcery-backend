package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.movement.view.request.MovementType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementManager {

    private final MapManager mapManager;

    @Autowired
    public MovementManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    //TODO: TEST!
    public boolean move(UserEntity user, MovementType type) {
        int targetX = getTargetXCoordinate(user, type);
        int targetY = getTargetYCoordinate(user, type);

        if (canWalk(user, targetX, targetY)) {
            user.setPosition(targetX, targetY);

            return true;
        }

        return false;
    }

    private int getTargetXCoordinate(UserEntity user, MovementType type) {
        switch (type) {
            case UP:
            case DOWN:
                return user.getX();
            case LEFT:
                return user.getX() - 1;
            case RIGHT:
                return user.getX() + 1;
        }

        throw new IllegalArgumentException();
    }

    private int getTargetYCoordinate(UserEntity user, MovementType type) {
        switch (type) {
            case UP:
                return user.getY() - 1;
            case DOWN:
                return user.getY() + 1;
            case LEFT:
            case RIGHT:
                return user.getY();
        }

        throw new IllegalArgumentException();
    }

    private boolean canWalk(UserEntity user, int x, int y) {
        //TODO: check if out of bounds of the map!
        //TODO: check has enough movement points!
        return mapManager.getMap(user.getMapId()).getTileAt(x, y).isWalkable();
    }
}
