package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentManager {

    private final EquipmentMapper equipmentMapper;

    @Autowired
    public EquipmentManager(EquipmentMapper equipmentMapper) {
        this.equipmentMapper = equipmentMapper;
    }

    public EquipmentEntity getEquipment(UserEntity userEntity) {
        return new EquipmentEntity(userEntity, equipmentMapper);
    }
}
