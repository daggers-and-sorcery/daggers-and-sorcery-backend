package com.morethanheroic.swords.explore.service.event.newevent.condition.impl;

import com.morethanheroic.swords.explore.service.event.newevent.condition.Condition;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemCondition implements Condition {

    private final ItemDefinition itemDefinition;
    private final int amount;
}
