package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.money.service.MoneyFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryFacade {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private JournalManager journalManager;

    @Autowired
    private MoneyFacade moneyFacade;

    public InventoryEntity getInventory(UserEntity userEntity) {
        return new InventoryEntity(userEntity, inventoryMapper, journalManager, moneyFacade);
    }
}
