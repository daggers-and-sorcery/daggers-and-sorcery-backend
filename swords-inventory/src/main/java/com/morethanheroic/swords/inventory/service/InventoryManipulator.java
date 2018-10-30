package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryManipulator {

    private final InventoryMapper inventoryMapper;

    public boolean hasItem(final UserEntity userEntity, final ItemDefinition item) {
        return hasItem(userEntity, item, IdentificationType.IDENTIFIED);
    }

    public boolean hasItem(final UserEntity userEntity, final ItemDefinition item,
            final IdentificationType identified) {
        return getItemAmount(userEntity, item, identified) > 0;
    }

    public void removeItem(final UserEntity userEntity, final ItemDefinition item, final int amount) {
        removeItem(userEntity, item, amount, IdentificationType.IDENTIFIED);
    }

    public void removeItem(final UserEntity userEntity, final ItemDefinition item, final int amount,
            final IdentificationType identified) {
        final int amountBeforeRemove = getItemAmount(userEntity, item, identified);

        if (amountBeforeRemove - amount > 0) {
            inventoryMapper.removeItem(userEntity.getId(), item.getId(), amountBeforeRemove - amount, identified.getId());
        } else {
            inventoryMapper.deleteItem(userEntity.getId(), item.getId(), identified.getId());
        }
    }

    public int getItemAmount(final UserEntity userEntity, final ItemDefinition item,
            final IdentificationType identified) {
        final ItemDatabaseEntity dbEntity = inventoryMapper.getItem(userEntity.getId(), item.getId(), identified.getId());

        return dbEntity != null ? dbEntity.getAmount() : 0;
    }
}
