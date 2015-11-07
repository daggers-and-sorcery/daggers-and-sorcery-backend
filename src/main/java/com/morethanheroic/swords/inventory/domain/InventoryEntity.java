package com.morethanheroic.swords.inventory.domain;

import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.user.domain.UserEntity;

import java.util.List;

public class InventoryEntity {

    private final JournalManager journalManager;
    private final UserEntity userEntity;
    private final InventoryMapper inventoryMapper;

    public InventoryEntity(UserEntity userEntity, InventoryMapper inventoryMapper, JournalManager journalManager) {
        this.userEntity = userEntity;
        this.inventoryMapper = inventoryMapper;
        this.journalManager = journalManager;
    }

    public boolean hasItem(int itemId) {
        return getItemAmount(itemId) > 0;
    }

    public boolean hasItemAmount(int itemId, int amount) {
        return getItemAmount(itemId) > amount;
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

    public void addItem(ItemDefinition item, int amount) {
        addItem(item.getId(), amount);
    }

    public void addItem(int itemId, int amount) {
        journalManager.createJournalEntry(userEntity, JournalType.ITEM, itemId);

        inventoryMapper.addItem(userEntity.getId(), itemId, amount);
    }

    public void removeItem(ItemDefinition item, int amount) {
        removeItem(item.getId(), amount);
    }

    public void removeItem(int itemId, int amount) {
        int amountBeforeRemove = getItemAmount(itemId);

        if (amountBeforeRemove - amount > 0) {
            inventoryMapper.removeItem(userEntity.getId(), itemId, amountBeforeRemove - amount);
        } else {
            inventoryMapper.deleteItem(userEntity.getId(), itemId);
        }
    }
}
