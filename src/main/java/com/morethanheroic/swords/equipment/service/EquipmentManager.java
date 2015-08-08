package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class EquipmentManager {

    private final InventoryManager inventoryManager;

    public EquipmentManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public boolean equip(UserEntity user, int itemId) {
        if (inventoryManager.getInventory(user).hasItem(itemId)) {
            //equip it

            return true;
        }

        return false;
    }
}
