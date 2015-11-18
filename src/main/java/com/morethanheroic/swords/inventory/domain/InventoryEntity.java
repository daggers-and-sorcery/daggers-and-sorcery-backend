package com.morethanheroic.swords.inventory.domain;

import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.user.domain.UserEntity;

import java.util.List;

//TODO: instead of true or false use an enum like IdentificationType.IDENTIFIED
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
        return hasItem(itemId, true);
    }

    public boolean hasItem(int itemId, boolean identified) {
        return getItemAmount(itemId, identified) > 0;
    }

    public boolean hasItemAmount(int itemId, int amount) {
        return hasItemAmount(itemId, amount, true);
    }

    public boolean hasItemAmount(int itemId, int amount, boolean identified) {
        return getItemAmount(itemId, identified) > amount;
    }

    public int getItemAmount(int itemId) {
        return getItemAmount(itemId, true);
    }

    public int getItemAmount(int itemId, boolean identified) {
        ItemDatabaseEntity dbEntity = inventoryMapper.getItem(userEntity.getId(), itemId, identified);

        if (dbEntity != null) {
            return dbEntity.getAmount();
        }

        return 0;
    }

    public void addItem(ItemDefinition item, int amount) {
        addItem(item.getId(), amount, true);
    }

    public void addItem(ItemDefinition item, int amount, boolean identified) {
        addItem(item.getId(), amount, identified);
    }

    public void addItem(int itemId, int amount) {
        addItem(itemId, amount, true);
    }

    public void addItem(int itemId, int amount, boolean identified) {
        if (identified) {
            journalManager.createJournalEntry(userEntity, JournalType.ITEM, itemId);
        }

        inventoryMapper.addItem(userEntity.getId(), itemId, amount, identified);
    }

    public void removeItem(ItemDefinition item, int amount) {
        removeItem(item.getId(), amount, true);
    }

    public void removeItem(ItemDefinition item, int amount, boolean identified) {
        removeItem(item.getId(), amount, identified);
    }

    public void removeItem(int itemId, int amount) {
        removeItem(itemId, amount, true);
    }

    public void removeItem(int itemId, int amount, boolean identified) {
        int amountBeforeRemove = getItemAmount(itemId, identified);

        if (amountBeforeRemove - amount > 0) {
            inventoryMapper.removeItem(userEntity.getId(), itemId, amountBeforeRemove - amount, identified);
        } else {
            inventoryMapper.deleteItem(userEntity.getId(), itemId, identified);
        }
    }

    public List<ItemDatabaseEntity> getItems() {
        return inventoryMapper.getItems(userEntity.getId());
    }

    public List<ItemDatabaseEntity> getItems(boolean identified) {
        return inventoryMapper.getItems(userEntity.getId(), identified);
    }
}
