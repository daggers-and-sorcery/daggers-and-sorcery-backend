package com.morethanheroic.swords.explore.service.event.newevent.condition;

import org.springframework.stereotype.Service;

@Service
public class ConditionFactory {

    public ConditionBuilder newConditionBuilder() {
        return new ConditionBuilder();
    }
}
