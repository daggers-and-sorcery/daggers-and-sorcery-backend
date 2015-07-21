package com.swordssorcery.server.game.movement;

import com.swordssorcery.server.controller.character.movement.request.MovementType;
import com.swordssorcery.server.model.entity.user.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class MovementManager {

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
                return user.getXPosition();
            case LEFT:
                return user.getXPosition() - 1;
            case RIGHT:
                return user.getXPosition() + 1;
        }

        throw new IllegalArgumentException();
    }

    private int getTargetYCoordinate(UserEntity user, MovementType type) {
        switch (type) {
            case UP:
                return user.getYPosition() - 1;
            case DOWN:
                return user.getYPosition() + 1;
            case LEFT:
            case RIGHT:
                return user.getYPosition();
        }

        throw new IllegalArgumentException();
    }

    private boolean canWalk(UserEntity user, int x, int y) {
        //TODO: check if out of bounds of the map!
        //TODO: check has enough movement points!
        return user.getMap().getTileAt(x, y).isWalkable();
    }
}
