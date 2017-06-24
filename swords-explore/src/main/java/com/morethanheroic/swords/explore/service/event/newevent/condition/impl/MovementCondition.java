package com.morethanheroic.swords.explore.service.event.newevent.condition.impl;

import com.morethanheroic.swords.explore.service.event.newevent.condition.Condition;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MovementCondition implements Condition {

    private final int amount;
}
