package com.morethanheroic.swords.explore.service.event.evaluator;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.probe.AttributeProbeCalculator;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.AttributeAttemptEventEntryEvaluatorResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttributeAttemptEventEntryEvaluator {

    private final AttributeProbeCalculator attributeProbeCalculator;

    public AttributeAttemptEventEntryEvaluatorResult attributeAttempt(final UserEntity userEntity, final Attribute attribute, final int valueToHit) {
        final boolean result = attributeProbeCalculator.calculateAttributeProbe(userEntity, attribute, valueToHit);

        return AttributeAttemptEventEntryEvaluatorResult.builder()
                .result(
                        TextExplorationEventEntryResult.builder()
                                .content("**" + attribute.getName() + " attempt against " + valueToHit + "! You rolled " + result + ". " + evaluateResultAsText(result) + "**")
                                .build()
                )
                .successful(result)
                .build();
    }

    private String evaluateResultAsText(final boolean result) {
        return result ? "Success!" : "Failure!";
    }
}
