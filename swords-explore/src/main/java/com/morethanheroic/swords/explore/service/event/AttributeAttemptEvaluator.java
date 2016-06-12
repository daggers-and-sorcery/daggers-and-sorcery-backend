package com.morethanheroic.swords.explore.service.event;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AttributeAttemptEvaluator {

    @Autowired
    private Random random;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    public boolean attributeAttempt(UserEntity userEntity, ExplorationResult explorationResult, Attribute attribute, int valueToHit) {
        final int attributeValue = globalAttributeCalculator.calculateAttributeValue(userEntity, attribute).getActual().getValue();

        final int result = (int) (random.nextInt((int) (attributeValue * 0.5)) + attributeValue * 0.5);

        explorationResult.addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("**" + attribute.getName() + " attempt against " + valueToHit + "! You rolled " + result + ". " + evaluateResultAsText(result, valueToHit) + "**")
                        .build()
        );

        return result >= valueToHit;
    }

    private String evaluateResultAsText(final int result, final int valueToHit) {
        return result >= valueToHit ? "Success!" : "Failure!";
    }
}
