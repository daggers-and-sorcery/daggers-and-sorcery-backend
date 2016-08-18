package com.morethanheroic.swords.explore.service.event.evaluator;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.AttributeAttemptEventEntryEvaluatorResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AttributeAttemptEventEntryEvaluator {

    @Autowired
    private Random random;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    public AttributeAttemptEventEntryEvaluatorResult attributeAttempt(final UserEntity userEntity, final Attribute attribute, final int valueToHit) {
        final int attributeValue = globalAttributeCalculator.calculateAttributeValue(userEntity, attribute).getActual().getValue();

        final int result = (int) (random.nextInt((int) (attributeValue * 0.5)) + attributeValue * 0.5);

        return AttributeAttemptEventEntryEvaluatorResult.builder()
                .result(
                        TextExplorationEventEntryResult.builder()
                                .content("**" + attribute.getName() + " attempt against " + valueToHit + "! You rolled " + result + ". " + evaluateResultAsText(result, valueToHit) + "**")
                                .build()
                )
                .successful(result >= valueToHit)
                .build();
    }

    private String evaluateResultAsText(final int result, final int valueToHit) {
        return result >= valueToHit ? "Success!" : "Failure!";
    }
}
