package com.morethanheroic.swords.explore.service.event.newevent.condition;

import com.morethanheroic.swords.explore.service.event.newevent.condition.impl.ItemCondition;
import com.morethanheroic.swords.explore.service.event.newevent.condition.impl.MoneyCondition;
import com.morethanheroic.swords.explore.service.event.newevent.condition.impl.MovementCondition;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ConditionBuilder {

    private final List<Condition> conditions = new ArrayList<>();

    public ConditionBuilder newItemCondition(final ItemDefinition item) {
        conditions.add(
                ItemCondition.builder()
                        .itemDefinition(item)
                        .amount(1)
                        .build()
        );

        return this;
    }

    public ConditionBuilder newItemCondition(final ItemDefinition item, final int amount) {
        conditions.add(
                ItemCondition.builder()
                        .itemDefinition(item)
                        .amount(amount)
                        .build()
        );

        return this;
    }

    public ConditionBuilder newMoneyCondition(final MoneyType type, final int amount) {
        conditions.add(
                MoneyCondition.builder()
                        .type(type)
                        .amount(amount)
                        .build()
        );

        return this;
    }

    public ConditionBuilder newMovementCondition(final int requiredAmount) {
        conditions.add(
                MovementCondition.builder()
                        .amount(requiredAmount)
                        .build()
        );

        return this;
    }

    public List<Condition> build() {
        return conditions;
    }
}
