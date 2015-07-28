package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryEntity {

    private UserDatabaseEntity userDatabaseEntity;

    public InventoryEntity(UserDatabaseEntity userDatabaseEntity) {
        this.userDatabaseEntity = userDatabaseEntity;
    }

    public void addItem(int itemId, int amount) {
        if(userDatabaseEntity.getInventory().containsKey(itemId)) {
            userDatabaseEntity.getInventory().get(itemId).increaseAmount(amount);
        } else {
            userDatabaseEntity.getInventory().put(itemId, new ItemDatabaseEntity(itemId, amount));
        }
    }

    public void removeItem(int itemId, int amount) {
        ItemDatabaseEntity item = userDatabaseEntity.getInventory().get(itemId);

        if(item.getAmount() - amount <= 0) {
            userDatabaseEntity.getInventory().remove(itemId);
        } else {
            item.decreaseAmount(amount);
        }
    }

    public List<ItemDatabaseEntity> getItemList() {
        return Collections.unmodifiableList(new ArrayList<>(userDatabaseEntity.getInventory().values()));
    }
}
