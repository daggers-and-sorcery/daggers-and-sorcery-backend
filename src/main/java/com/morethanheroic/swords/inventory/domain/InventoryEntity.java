package com.morethanheroic.swords.inventory.domain;

import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.user.domain.UserEntity;

import java.util.List;

public class InventoryEntity {

    private UserEntity userEntity;
    private InventoryMapper inventoryMapper;

    public InventoryEntity(UserEntity userEntity, InventoryMapper inventoryMapper) {
        this.userEntity = userEntity;
        this.inventoryMapper = inventoryMapper;
    }

    public boolean hasItem(int itemId) {
        return inventoryMapper.getItem(userEntity.getId(), itemId) != null;
    }

    public List<ItemDatabaseEntity> getItems() {
        return inventoryMapper.getItems(userEntity.getId());
    }

    public void addItem(int itemId, int itemAmount) {
        inventoryMapper.addItem(userEntity.getId(), itemId, itemAmount);
    }
}
