package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlotMapper;
import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentManager {

    private final InventoryManager inventoryManager;
    private final EquipmentMapper equipmentMapper;
    private final EquipmentSlotMapper equipmentSlotMapper;

    @Autowired
    public EquipmentManager(InventoryManager inventoryManager, EquipmentMapper equipmentMapper, EquipmentSlotMapper equipmentSlotMapper) {
        this.inventoryManager = inventoryManager;
        this.equipmentMapper = equipmentMapper;
        this.equipmentSlotMapper = equipmentSlotMapper;
    }

    public EquipmentEntity getEquipment(UserEntity userEntity) {
        return new EquipmentEntity(userEntity, inventoryManager.getInventory(userEntity), equipmentMapper, equipmentSlotMapper);
    }
}
