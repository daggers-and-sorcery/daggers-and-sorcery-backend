package com.morethanheroic.swords.inventory.service.pouch;

import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.service.pouch.domain.MoneyPouch;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Create money pouch instances for a user.
 */
@Service
@RequiredArgsConstructor
public class MoneyPouchFactory {

    private final InventoryEntityFactory inventoryEntityFactory;

    public MoneyPouch newMoneyPouch(final UserEntity userEntity, final MoneyType moneyType) {
        final int moneyAmount = inventoryEntityFactory.getEntity(userEntity)
                .getMoneyAmount(moneyType);

        return MoneyPouch.builder()
                .type(moneyType)
                .amount(moneyAmount)
                .build();
    }
}
