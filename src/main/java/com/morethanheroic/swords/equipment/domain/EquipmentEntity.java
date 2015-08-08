package com.morethanheroic.swords.equipment.domain;

import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.item.service.domain.ItemType;
import com.morethanheroic.swords.user.domain.UserEntity;

public class EquipmentEntity {

    private final UserEntity userEntity;
    private final EquipmentMapper equipmentMapper;

    public EquipmentEntity(UserEntity userEntity, EquipmentMapper equipmentMapper) {
        this.userEntity = userEntity;
        this.equipmentMapper = equipmentMapper;
    }

    public void equipItem(int itemId, ItemType type) {
        //TODO: add equipment code
    }
}
