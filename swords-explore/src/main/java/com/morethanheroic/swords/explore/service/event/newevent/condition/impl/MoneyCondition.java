package com.morethanheroic.swords.explore.service.event.newevent.condition.impl;

import com.morethanheroic.swords.explore.service.event.newevent.condition.Condition;
import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MoneyCondition implements Condition {

    private final MoneyType type;
    private final int amount;
}
