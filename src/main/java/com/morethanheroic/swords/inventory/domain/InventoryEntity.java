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
        return getItemAmount(itemId) > 0;
    }

    public int getItemAmount(int itemId) {
        ItemDatabaseEntity dbEntity = inventoryMapper.getItem(userEntity.getId(), itemId);

        if (dbEntity != null) {
            return dbEntity.getAmount();
        }

        return 0;
    }

    public List<ItemDatabaseEntity> getItems() {
        return inventoryMapper.getItems(userEntity.getId());
    }

    public void addItem(int itemId, int itemAmount) {
        inventoryMapper.addItem(userEntity.getId(), itemId, itemAmount);
    }

    public void removeItem(int itemId, int itemAmount) {
        int amount = getItemAmount(itemId);

        if (amount - itemAmount > 0) {
            inventoryMapper.removeItem(userEntity.getId(), itemId, amount - itemAmount);
        } else {
            inventoryMapper.deleteItem(userEntity.getId(), itemId);
        }
    }
}
