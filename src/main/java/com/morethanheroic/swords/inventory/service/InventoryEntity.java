package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;

import java.util.List;

public class InventoryEntity {

    private UserDatabaseEntity userDatabaseEntity;
    private InventoryMapper inventoryMapper;

    public InventoryEntity(UserDatabaseEntity userDatabaseEntity, InventoryMapper inventoryMapper) {
        this.userDatabaseEntity = userDatabaseEntity;
        this.inventoryMapper = inventoryMapper;
    }

    public void addItem(int itemId, int amount) {
        inventoryMapper.addItem(userDatabaseEntity.getId(), itemId, amount);
    }

    public void removeItem(int itemId, int amount) {
    }

    public List<ItemDatabaseEntity> getItemList() {
        return inventoryMapper.getItems(userDatabaseEntity.getId());
    }
}
