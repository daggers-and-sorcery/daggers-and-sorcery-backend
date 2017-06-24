package com.morethanheroic.swords.explore.service.event.newevent.condition;

import com.morethanheroic.swords.explore.service.event.newevent.condition.evaluator.ConditionEvaluator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MasterConditionEvaluator {

    private final Map<Class<Condition>, ConditionEvaluator> conditionEvaluators;

    public MasterConditionEvaluator(final List<ConditionEvaluator> conditionEvaluators) {
        this.conditionEvaluators = conditionEvaluators.stream()
                .collect(Collectors.toMap(ConditionEvaluator::getSupportedClass, Function.identity()));
    }

    public boolean evaluateConditions(final UserEntity userEntity, final List<Condition> conditions) {
        return conditions.stream()
                .noneMatch(condition -> conditionEvaluators.get(condition.getClass()).evaluate(userEntity, condition));
    }
}
