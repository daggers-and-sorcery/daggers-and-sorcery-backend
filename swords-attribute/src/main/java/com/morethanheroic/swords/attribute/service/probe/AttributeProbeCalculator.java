package com.morethanheroic.swords.attribute.service.probe;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.probe.domain.AttributeProbeCalculationResult;
import com.morethanheroic.swords.attribute.service.probe.extension.AttributeProbeCalculatorExtension;
import com.morethanheroic.swords.attribute.service.probe.extension.GlobalAttributeProbeCalculatorExtensionResultFactory;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.AttributeProbeCalculatorExtensionResult;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PostProbeHookData;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PreProbeHookData;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AttributeProbeCalculator {

    private final Random random;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final AttributeProbeCalculatorProvider attributeProbeCalculatorProvider;
    private final GlobalAttributeProbeCalculatorExtensionResultFactory globalAttributeProbeCalculatorExtensionResultFactory;

    public AttributeProbeCalculationResult calculateAttributeProbe(final UserEntity userEntity, final Attribute attribute, final int target) {
        final Optional<AttributeProbeCalculatorExtension<AttributeProbeCalculatorExtensionResult>> calculatorExtension = attributeProbeCalculatorProvider.getCalculatorExtension(attribute);
        final AttributeProbeCalculatorExtensionResult extensionResult = globalAttributeProbeCalculatorExtensionResultFactory.newExtensionResult(attribute);

        if (calculatorExtension.isPresent()) {
            if (!calculatorExtension.get().checkRequirements(extensionResult, userEntity)) {
                return AttributeProbeCalculationResult.builder()
                        .extensionResult(extensionResult)
                        .successful(false)
                        .build();
            }
        }

        calculatorExtension.ifPresent((extension) -> extension.preProbeHook(
                extensionResult,
                PreProbeHookData.builder()
                        .userEntity(userEntity)
                        .build()
        ));

        final int rolledValue = calculateAttributeValue(userEntity, attribute);
        final boolean result = calculateProbeResult(rolledValue) >= target;

        calculatorExtension.ifPresent((extension) -> extension.postProbeHook(
                extensionResult,
                PostProbeHookData.builder()
                        .userEntity(userEntity)
                        .targetToHit(target)
                        .successfulProbe(result)
                        .build()
        ));

        return AttributeProbeCalculationResult.builder()
                .extensionResult(extensionResult)
                .successful(result)
                .rolledValue(rolledValue)
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
