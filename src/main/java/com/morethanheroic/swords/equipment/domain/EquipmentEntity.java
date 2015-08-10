package com.morethanheroic.swords.equipment.domain;

import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.item.service.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

public class EquipmentEntity {

    private final UserEntity userEntity;
    private final InventoryEntity inventoryEntity;
    private final EquipmentMapper equipmentMapper;
    private final EquipmentSlotMapper equipmentSlotMapper;

    public EquipmentEntity(UserEntity userEntity, InventoryEntity inventoryEntity, EquipmentMapper equipmentMapper, EquipmentSlotMapper equipmentSlotMapper) {
        this.userEntity = userEntity;
        this.inventoryEntity = inventoryEntity;
        this.equipmentMapper = equipmentMapper;
        this.equipmentSlotMapper = equipmentSlotMapper;
    }

    public void equipItem(ItemDefinition item) {
        switch (equipmentSlotMapper.getEquipmentSlotFromItemType(item.getType())) {
            case WEAPON:
                int previousWeapon = equipmentMapper.getEquipment(userEntity.getId()).getWeapon();

                if(previousWeapon != 0) {
                    inventoryEntity.addItem(previousWeapon, 1);
                }

                equipmentMapper.equipWeapon(userEntity.getId(), item.getId());
                inventoryEntity.removeItem(item.getId(), 1);
                break;
            case OFFHAND:
                int previousOffhand = equipmentMapper.getEquipment(userEntity.getId()).getOffhand();

                if(previousOffhand != 0) {
                    inventoryEntity.addItem(previousOffhand, 1);
                }
                equipmentMapper.equipOffhand(userEntity.getId(), item.getId());
                inventoryEntity.removeItem(item.getId(), 1);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
