package com.morethanheroic.swords.attribute.service.attempt;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.attempt.domain.AttributeAttemptCalculationResult;
import com.morethanheroic.swords.attribute.service.attempt.extension.AttributeAttemptCalculatorExtension;
import com.morethanheroic.swords.attribute.service.attempt.extension.GlobalAttributeAttemptCalculatorExtensionResultFactory;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.AttributeAttemptCalculatorExtensionResult;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.PostAttemptHookData;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.PreAttemptHookData;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AttributeAttemptCalculator {

    private final Random random;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final AttributeAttemptCalculatorProvider attributeAttemptCalculatorProvider;
    private final GlobalAttributeAttemptCalculatorExtensionResultFactory globalAttributeAttemptCalculatorExtensionResultFactory;

    public AttributeAttemptCalculationResult calculateAttributeAttempt(final UserEntity userEntity, final Attribute attribute, final int target) {
        final Optional<AttributeAttemptCalculatorExtension<AttributeAttemptCalculatorExtensionResult>> calculatorExtension = attributeAttemptCalculatorProvider
                .getCalculatorExtension(attribute);
        final AttributeAttemptCalculatorExtensionResult extensionResult = globalAttributeAttemptCalculatorExtensionResultFactory.newExtensionResult(attribute);

        if (calculatorExtension.isPresent()) {
            if (!calculatorExtension.get().checkRequirements(extensionResult, userEntity)) {
                return AttributeAttemptCalculationResult.builder()
                        .extensionResult(extensionResult)
                        .successful(false)
                        .build();
            }
        }

        calculatorExtension.ifPresent((extension) -> extension.preProbeHook(
                extensionResult,
                PreAttemptHookData.builder()
                        .userEntity(userEntity)
                        .build()
        ));

        final int attributeValue = calculateAttributeValue(userEntity, attribute);
        final int rolledResult = calculateProbeResult(attributeValue);
        final boolean result = rolledResult >= target;

        calculatorExtension.ifPresent((extension) -> extension.postProbeHook(
                extensionResult,
                PostAttemptHookData.builder()
                        .userEntity(userEntity)
                        .targetToHit(target)
                        .successfulProbe(result)
                        .build()
        ));

        return AttributeAttemptCalculationResult.builder()
                .extensionResult(extensionResult)
                .successful(result)
                .rolledValue(rolledResult)
                .build();
    }

    private int calculateAttributeValue(final UserEntity userEntity, final Attribute attribute) {
        return globalAttributeCalculator.calculateAttributeValue(userEntity, attribute).getActual().getValue();
    }

    private int calculateProbeResult(int attributeValue) {
        if(attributeValue <= 1) {
            return 0;
        }

        return (int) (random.nextInt((int) (attributeValue * 0.5)) + attributeValue * 0.5);
    }
}
