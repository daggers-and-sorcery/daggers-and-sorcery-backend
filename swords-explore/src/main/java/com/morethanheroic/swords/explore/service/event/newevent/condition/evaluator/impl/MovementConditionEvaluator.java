package com.morethanheroic.swords.explore.service.event.newevent.condition.evaluator.impl;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.BasicAttributeCalculator;
import com.morethanheroic.swords.explore.service.event.newevent.condition.evaluator.ConditionEvaluator;
import com.morethanheroic.swords.explore.service.event.newevent.condition.impl.MovementCondition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovementConditionEvaluator implements ConditionEvaluator<MovementCondition> {

    private final BasicAttributeCalculator basicAttributeCalculator;

    @Override
    public boolean evaluate(final UserEntity userEntity, final MovementCondition condition) {
        return basicAttributeCalculator.calculateAttributeValue(userEntity, BasicAttribute.MOVEMENT).getActual().getValue() >= condition.getAmount();
    }

    @Override
    public Class<MovementCondition> getSupportedClass() {
        return MovementCondition.class;
    }
}
