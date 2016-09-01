package com.morethanheroic.swords.explore.service.event.evaluator.attempt;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.attempt.AttributeAttemptCalculator;
import com.morethanheroic.swords.attribute.service.attempt.domain.AttributeAttemptCalculationResult;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.event.evaluator.attempt.domain.AttributeAttemptEventEntryEvaluatorResult;
import com.morethanheroic.swords.explore.service.event.evaluator.attempt.result.AttributeAttemptEntryResultFactoryProvider;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeAttemptEventEntryEvaluator {

    private final AttributeAttemptCalculator attributeAttemptCalculator;
    private final AttributeAttemptEntryResultFactoryProvider attributeAttemptEntryResultFactoryProvider;

    public AttributeAttemptEventEntryEvaluatorResult attributeAttempt(final UserEntity userEntity, final Attribute attribute, final int valueToHit) {
        final AttributeAttemptCalculationResult calculationResult = attributeAttemptCalculator
            .calculateAttributeAttempt(userEntity, attribute, valueToHit);

        final List<ExplorationEventEntryResult> explorationResult = attributeAttemptEntryResultFactoryProvider.getFactory(calculationResult).newResult(attribute, valueToHit, calculationResult);

        return AttributeAttemptEventEntryEvaluatorResult.builder()
                .result(explorationResult)
                .successful(calculationResult.isSuccessful())
                .build();
    }
}
