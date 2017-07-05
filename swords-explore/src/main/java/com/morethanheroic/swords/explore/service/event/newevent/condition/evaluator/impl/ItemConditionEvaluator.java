package com.morethanheroic.swords.explore.service.event.newevent.condition.evaluator.impl;

import com.morethanheroic.swords.explore.service.event.newevent.condition.evaluator.ConditionEvaluator;
import com.morethanheroic.swords.explore.service.event.newevent.condition.impl.ItemCondition;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemConditionEvaluator implements ConditionEvaluator<ItemCondition> {

    private final InventoryEntityFactory inventoryEntityFactory;

    @Override
    public boolean evaluate(final UserEntity userEntity, final ItemCondition condition) {
        return inventoryEntityFactory.getEntity(userEntity).hasItemAmount(condition.getItemDefinition(), condition.getAmount(), IdentificationType.IDENTIFIED);
    }

    @Override
    public Class<ItemCondition> getSupportedClass() {
        return ItemCondition.class;
    }
}
