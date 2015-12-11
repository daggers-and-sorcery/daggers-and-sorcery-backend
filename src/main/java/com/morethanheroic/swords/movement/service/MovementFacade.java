package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.movement.domain.MovementEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementFacade {

    @Autowired
    private MapManager mapManager;

    @Autowired
    private UserMapper userMapper;

    public MovementEntity getEntity(UserEntity userEntity) {
        return new MovementEntity(userEntity.getUserDatabaseEntity(), userMapper, mapManager);
    }
}
