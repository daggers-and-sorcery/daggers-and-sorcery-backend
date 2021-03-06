package com.morethanheroic.swords.equipment;

import com.morethanheroic.dependencyinjection.inject.InjectAtReturn;
import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.memoize.Memoize;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

/**
 * Factory class for creating {@link EquipmentEntity} instances.
 */
@Service
public class EquipmentEntityFactory implements EntityFactory<EquipmentEntity, UserEntity> {

    /**
     * Create an {@link EquipmentEntity} for the provided {@link UserEntity}.
     */
    @Override
    @Memoize
    @InjectAtReturn
    public EquipmentEntity getEntity(final UserEntity userEntity) {
        return new EquipmentEntity(userEntity);
    }
}
