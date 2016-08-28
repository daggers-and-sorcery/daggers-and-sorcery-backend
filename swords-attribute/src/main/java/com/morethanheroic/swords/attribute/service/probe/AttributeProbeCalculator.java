package com.morethanheroic.swords.attribute.service.probe;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.probe.extension.AttributeProbeCalculatorExtension;
import com.morethanheroic.swords.attribute.service.probe.extension.GlobalAttributeProbeCalculatorExtensionResultFactory;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.AttributeProbeCalculatorExtensionResult;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PostProbeHookData;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PreProbeHookData;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.RequirementsData;
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

    public boolean calculateAttributeProbe(final UserEntity userEntity, final Attribute attribute, final int target) {
        final Optional<AttributeProbeCalculatorExtension> calculatorExtension = attributeProbeCalculatorProvider.getCalculatorExtension(attribute);
        final AttributeProbeCalculatorExtensionResult extensionResult = globalAttributeProbeCalculatorExtensionResultFactory.newExtensionResult(attribute);

        if (calculatorExtension.isPresent()) {
            if (!calculatorExtension.get().checkRequirements(
                    RequirementsData.builder()
                            .userEntity(userEntity)
                            .extensionResult(extensionResult)
                            .build()
            )) {
                return false;
            }
        }

        calculatorExtension.ifPresent((extension) -> extension.preProbeHook(
                PreProbeHookData.builder()
                        .extensionResult(extensionResult)
                        .build()
        ));

        final boolean result = calculateProbeResult(calculateAttributeValue(userEntity, attribute)) >= target;

        calculatorExtension.ifPresent((extension) -> extension.postProbeHook(
                PostProbeHookData.builder()
                        .successfulProbe(result)
                        .extensionResult(extensionResult)
                        .build()
        ));

        return result;
    }

    private int calculateAttributeValue(final UserEntity userEntity, final Attribute attribute) {
        return globalAttributeCalculator.calculateAttributeValue(userEntity, attribute).getActual().getValue();
    }

    private int calculateProbeResult(int attributeValue) {
        return (int) (random.nextInt((int) (attributeValue * 0.5)) + attributeValue * 0.5);
    }
}
