package com.morethanheroic.swords.explore.service.event.newevent.condition.evaluator.impl;

import com.morethanheroic.swords.explore.service.event.newevent.condition.evaluator.ConditionEvaluator;
import com.morethanheroic.swords.explore.service.event.newevent.condition.impl.MoneyCondition;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoneyConditionEvaluator implements ConditionEvaluator<MoneyCondition> {

    private final InventoryEntityFactory inventoryEntityFactory;

    @Override
    public boolean evaluate(final UserEntity userEntity, final MoneyCondition condition) {
        return inventoryEntityFactory.getEntity(userEntity).hasMoneyAmount(condition.getType(), condition.getAmount());
    }

    @Override
    public Class<MoneyCondition> getSupportedClass() {
        return MoneyCondition.class;
    }
}
