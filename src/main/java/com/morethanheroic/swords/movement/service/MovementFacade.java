package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.movement.domain.MovementEntity;
import com.morethanheroic.swords.movement.repository.domain.MovementMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementFacade {

    private static final int STARTING_POSITION_X = 6;
    private static final int STARTING_POSITION_Y = 9;
    private static final int STARTING_MAP_ID = 1;

    @Autowired
    private MapManager mapManager;

    @Autowired
    private MovementMapper movementMapper;

    public MovementEntity getEntity(UserEntity userEntity) {
        return new MovementEntity(movementMapper.findEntity(userEntity.getId()), movementMapper, mapManager);
    }

    public void createMovementForUser(UserEntity userEntity) {
        movementMapper.createNewPosition(userEntity.getId(), STARTING_MAP_ID, STARTING_POSITION_X, STARTING_POSITION_Y);
    }
}
