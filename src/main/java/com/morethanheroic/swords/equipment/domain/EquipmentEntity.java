package com.morethanheroic.swords.equipment.domain;

import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.item.service.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

public class EquipmentEntity {

    private final UserEntity userEntity;
    private final EquipmentMapper equipmentMapper;
    private final EquipmentSlotMapper equipmentSlotMapper;

    public EquipmentEntity(UserEntity userEntity, EquipmentMapper equipmentMapper, EquipmentSlotMapper equipmentSlotMapper) {
        this.userEntity = userEntity;
        this.equipmentMapper = equipmentMapper;
        this.equipmentSlotMapper = equipmentSlotMapper;
    }

    public void equipItem(ItemDefinition item) {
        switch (equipmentSlotMapper.getEquipmentSlotFromItemType(item.getType())) {
            case WEAPON:
                equipmentMapper.equipWeapon(userEntity.getId(), item.getId());
                //TODO: add back previous equipment!
                break;
            case OFFHAND:
                equipmentMapper.equipOffhand(userEntity.getId(), item.getId());
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
