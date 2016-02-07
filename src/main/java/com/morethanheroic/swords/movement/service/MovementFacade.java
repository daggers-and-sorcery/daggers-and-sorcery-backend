package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.movement.domain.MovementEntity;
import com.morethanheroic.swords.movement.repository.domain.MovementMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovementFacade {

    @NonNull
    private final MapManager mapManager;

    @NonNull
    private final MovementMapper movementMapper;

    public MovementEntity getEntity(UserEntity userEntity) {
        return new MovementEntity(movementMapper.findEntity(userEntity.getId()), movementMapper, mapManager);
    }
}
