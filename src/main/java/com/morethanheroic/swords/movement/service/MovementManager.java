package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.movement.domain.MovementEntity;
import com.morethanheroic.swords.movement.view.request.MovementType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementManager {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private MovementFacade movementFacade;

    public boolean move(UserEntity userEntity, MovementType type) {
        MovementEntity movementEntity = movementFacade.getEntity(userEntity);

        int targetX = getTargetXCoordinate(movementEntity, type);
        int targetY = getTargetYCoordinate(movementEntity, type);

        if (canWalk(userEntity, movementEntity, targetX, targetY)) {
            movementEntity.setPosition(targetX, targetY);

            userEntity.setMovementPoints(userEntity.getMovementPoints() - 1);

            return true;
        }

        return false;
    }

    private int getTargetXCoordinate(MovementEntity movementEntity, MovementType type) {
        switch (type) {
            case UP:
            case DOWN:
                return movementEntity.getX();
            case LEFT:
                return movementEntity.getX() - 1;
            case RIGHT:
                return movementEntity.getX() + 1;
        }

        throw new IllegalArgumentException();
    }

    private int getTargetYCoordinate(MovementEntity movementEntity, MovementType type) {
        switch (type) {
            case UP:
                return movementEntity.getY() - 1;
            case DOWN:
                return movementEntity.getY() + 1;
            case LEFT:
            case RIGHT:
                return movementEntity.getY();
        }

        throw new IllegalArgumentException();
    }

    private boolean canWalk(UserEntity user, MovementEntity movementEntity, int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }

        if (globalAttributeCalculator.calculateActualValue(user, BasicAttribute.MOVEMENT).getValue() <= 0) {
            return false;
        }

        //TODO: Map should have a isTileWalkable instead of tis train
        return movementEntity.getMap().getTileAt(x, y).isWalkable();
    }
}
