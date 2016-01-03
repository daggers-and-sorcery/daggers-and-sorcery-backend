package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.dependency.InjectAtReturn;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.memoize.Memoize;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentFacade {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Memoize
    @InjectAtReturn
    public EquipmentEntity getEquipment(UserEntity userEntity) {
        return new EquipmentEntity(userEntity);
    }

    public void createEquipmentForUser(UserEntity userEntity) {
        equipmentMapper.insert(userEntity.getId());
    }
}
