package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentManager {

    private final InventoryManager inventoryManager;
    private final ItemDefinitionManager itemDefinitionManager;

    @Autowired
    public EquipmentManager(InventoryManager inventoryManager, ItemDefinitionManager itemDefinitionManager) {
        this.inventoryManager = inventoryManager;
        this.itemDefinitionManager = itemDefinitionManager;
    }

    public boolean equip(UserEntity user, int itemId) {
        if (inventoryManager.getInventory(user).hasItem(itemId) && itemDefinitionManager.getItemDefinition(itemId).isEquipment()) {
            //equip it

            return true;
        }

        return false;
    }
}
