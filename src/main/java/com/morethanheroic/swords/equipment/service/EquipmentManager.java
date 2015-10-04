package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlotMapper;
import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class EquipmentManager {

    private final InventoryManager inventoryManager;
    private final EquipmentMapper equipmentMapper;
    private final EquipmentSlotMapper equipmentSlotMapper;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final ItemDefinitionManager itemDefinitionManager;

    @Autowired
    public EquipmentManager(InventoryManager inventoryManager, EquipmentMapper equipmentMapper, EquipmentSlotMapper equipmentSlotMapper, GlobalAttributeCalculator globalAttributeCalculator, ItemDefinitionManager itemDefinitionManager) {
        this.inventoryManager = inventoryManager;
        this.equipmentMapper = equipmentMapper;
        this.equipmentSlotMapper = equipmentSlotMapper;
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.itemDefinitionManager = itemDefinitionManager;
    }

    public EquipmentEntity getEquipment(UserEntity userEntity) {
        return new EquipmentEntity(userEntity, inventoryManager.getInventory(userEntity), equipmentMapper, equipmentSlotMapper, globalAttributeCalculator, itemDefinitionManager);
    }
}
