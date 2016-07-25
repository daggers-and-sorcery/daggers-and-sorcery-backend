package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.swords.inventory.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @deprecated Don't use this class.
 */
@Service
public class InventoryFacade {

    @Autowired
    private InventoryEntityFactory inventoryEntityFactory;

    /**
     * @deprecated Use {@link InventoryEntityFactory#getEntity(int)} instead.
     */
    @Deprecated
    public InventoryEntity getInventory(UserEntity userEntity) {
        return inventoryEntityFactory.getEntity(userEntity.getId());
    }
}
