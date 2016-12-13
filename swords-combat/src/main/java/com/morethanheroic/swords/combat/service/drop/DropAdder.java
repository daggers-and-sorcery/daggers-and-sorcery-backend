package com.morethanheroic.swords.combat.service.drop;

import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DropAdder {

    private final InventoryEntityFactory inventoryEntityFactory;

    public void addDrops(final UserEntity userEntity, final List<Drop> drops) {
        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

        drops.forEach(drop -> inventoryEntity.addItem(drop.getItem(), drop.getAmount(), drop.isIdentified()));
    }
}
