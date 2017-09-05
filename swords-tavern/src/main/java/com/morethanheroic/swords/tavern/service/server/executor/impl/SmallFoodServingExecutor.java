package com.morethanheroic.swords.tavern.service.server.executor.impl;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.tavern.domain.service.ServiceType;
import com.morethanheroic.swords.tavern.service.server.context.impl.DefaultServingContext;
import com.morethanheroic.swords.tavern.service.server.executor.ServingExecutor;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmallFoodServingExecutor implements ServingExecutor<DefaultServingContext> {

    private static final MoneyType REQUIRED_MONEY_TYPE = MoneyType.MONEY;
    private static final int REQUIRED_MONEY_AMOUNT = 3;
    private static final int REQUIRED_MOVEMENT_POINT_AMOUNT = 1;
    private static final int HEALTH_REGENERATION_AMOUNT = 5;
    private static final int MANA_REGENERATION_AMOUNT = 2;

    private final InventoryEntityFactory inventoryEntityFactory;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;

    @Override
    public boolean canExecute(final DefaultServingContext servingContext) {
        final UserEntity userEntity = servingContext.getUserEntity();

        return inventoryEntityFactory.getEntity(userEntity.getId()).hasMoneyAmount(REQUIRED_MONEY_TYPE, REQUIRED_MONEY_AMOUNT)
                && userEntity.getMovementPoints() >= REQUIRED_MOVEMENT_POINT_AMOUNT;
    }

    @Override
    public void executeServing(final DefaultServingContext servingContext) {
        final UserEntity userEntity = servingContext.getUserEntity();

        userBasicAttributeManipulator.decreaseMovement(userEntity, REQUIRED_MOVEMENT_POINT_AMOUNT);
        userBasicAttributeManipulator.increaseHealth(userEntity, HEALTH_REGENERATION_AMOUNT);
        userBasicAttributeManipulator.increaseMana(userEntity, MANA_REGENERATION_AMOUNT);

        inventoryEntityFactory.getEntity(userEntity.getId()).decreaseMoneyAmount(REQUIRED_MONEY_TYPE, REQUIRED_MONEY_AMOUNT);
    }

    @Override
    public ServiceType supportedService() {
        return ServiceType.SMALL_SERVING_FOOD;
    }
}
