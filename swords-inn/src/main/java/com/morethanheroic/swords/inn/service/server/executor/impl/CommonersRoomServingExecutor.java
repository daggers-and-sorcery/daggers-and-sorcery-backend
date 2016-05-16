package com.morethanheroic.swords.inn.service.server.executor.impl;

import com.morethanheroic.swords.inn.domain.ServiceType;
import com.morethanheroic.swords.inn.service.server.context.impl.DefaultServingContext;
import com.morethanheroic.swords.inn.service.server.executor.ServingExecutor;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonersRoomServingExecutor implements ServingExecutor<DefaultServingContext> {

    private static final MoneyType REQUIRED_MONEY_TYPE = MoneyType.MONEY;
    private static final int REQUIRED_MONEY_AMOUNT = 7;
    private static final int REQUIRED_MOVEMENT_POINT_AMOUNT = 2;
    private static final int HEALED_AMOUNT = 12;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Override
    public boolean canExecute(final DefaultServingContext servingContext) {
        final UserEntity userEntity = servingContext.getUserEntity();

        return inventoryFacade.getInventory(userEntity).getMoneyAmount(REQUIRED_MONEY_TYPE) > REQUIRED_MONEY_AMOUNT && userEntity.getMovementPoints() >= REQUIRED_MOVEMENT_POINT_AMOUNT;
    }

    @Override
    public void executeServing(final DefaultServingContext servingContext) {
        final UserEntity userEntity = servingContext.getUserEntity();

        userEntity.setMovementPoints(userEntity.getMovementPoints() - REQUIRED_MOVEMENT_POINT_AMOUNT);
        userEntity.setHealthPoints(userEntity.getHealthPoints() + HEALED_AMOUNT);

        inventoryFacade.getInventory(userEntity).decreaseMoneyAmount(MoneyType.MONEY, REQUIRED_MONEY_AMOUNT);
    }

    @Override
    public ServiceType supportedService() {
        return ServiceType.COMMON_ROOM;
    }
}
