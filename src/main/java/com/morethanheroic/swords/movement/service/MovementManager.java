package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.movement.view.request.MovementType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementManager {

    private final MapManager mapManager;
    private final GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    public MovementManager(MapManager mapManager, GlobalAttributeCalculator globalAttributeCalculator) {
        this.mapManager = mapManager;
        this.globalAttributeCalculator = globalAttributeCalculator;
    }

    //TODO: TEST!
    public boolean move(UserEntity user, MovementType type) {
        int targetX = getTargetXCoordinate(user, type);
        int targetY = getTargetYCoordinate(user, type);

        if (canWalk(user, targetX, targetY)) {
            user.setPosition(targetX, targetY);
            user.setMovement(user.getMovement() - 1);

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
        if (x < 0 || y < 0) {
            return false;
        }

        if (globalAttributeCalculator.calculateActualValue(user, BasicAttribute.MOVEMENT) <= 0) {
            return false;
        }

        return mapManager.getMap(user.getMapId()).getTileAt(x, y).isWalkable();
    }
}
