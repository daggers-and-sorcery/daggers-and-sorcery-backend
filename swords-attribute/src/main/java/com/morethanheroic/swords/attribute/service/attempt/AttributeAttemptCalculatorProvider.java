package com.morethanheroic.swords.attribute.service.attempt;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.attempt.extension.AttributeAttemptCalculatorExtension;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.AttributeAttemptCalculatorExtensionResult;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AttributeAttemptCalculatorProvider {

    private final Map<Attribute, AttributeAttemptCalculatorExtension> calculatorExtensionMap;

    public AttributeAttemptCalculatorProvider(final List<AttributeAttemptCalculatorExtension> calculatorExtensions) {
        final Map<Attribute, AttributeAttemptCalculatorExtension> temporaryCalculatorExtensionMap = new HashMap<>();
        for (AttributeAttemptCalculatorExtension attributeProbeCalculator : calculatorExtensions) {
            temporaryCalculatorExtensionMap.put(attributeProbeCalculator.supportedAttribute(), attributeProbeCalculator);
        }
        calculatorExtensionMap = Collections.unmodifiableMap(temporaryCalculatorExtensionMap);
    }

    public Optional<AttributeAttemptCalculatorExtension<AttributeAttemptCalculatorExtensionResult>> getCalculatorExtension(final Attribute attribute) {
        final AttributeAttemptCalculatorExtension calculatorExtension = calculatorExtensionMap.get(attribute);

        if (calculatorExtension != null) {
            return Optional.of(calculatorExtension);
        }

        return Optional.empty();
    }
}
