package com.morethanheroic.swords.explore.service.event.newevent.condition.evaluator;

import com.morethanheroic.swords.explore.service.event.newevent.condition.Condition;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface ConditionEvaluator<T extends Condition> {

    boolean evaluate(final UserEntity userEntity, final T condition);

    Class<T> getSupportedClass();
}
