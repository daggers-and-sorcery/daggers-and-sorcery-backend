package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.dependency.InjectAtReturn;
import com.morethanheroic.swords.equipment.EquipmentEntityFactory;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.memoize.Memoize;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @deprecated Use {@link EquipmentEntityFactory} instead.
 */
@Deprecated
@Service
@RequiredArgsConstructor
public class EquipmentFacade {

    private final EquipmentEntityFactory equipmentEntityFactory;


    /**
     * @deprecated Use {@link EquipmentEntityFactory} instead.
     */
    @Memoize
    @InjectAtReturn
    public EquipmentEntity getEquipment(final UserEntity userEntity) {
        return equipmentEntityFactory.getEntity(userEntity);
    }
}
