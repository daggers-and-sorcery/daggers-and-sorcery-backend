package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.journal.repository.domain.JournalMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryManager {

    private final InventoryMapper inventoryMapper;
    private final JournalMapper journalMapper;

    @Autowired
    public InventoryManager(InventoryMapper inventoryMapper, JournalMapper journalMapper) {
        this.inventoryMapper = inventoryMapper;
        this.journalMapper = journalMapper;
    }

    public InventoryEntity getInventory(UserEntity userEntity) {
        return new InventoryEntity(userEntity, inventoryMapper, journalMapper);
    }
}
