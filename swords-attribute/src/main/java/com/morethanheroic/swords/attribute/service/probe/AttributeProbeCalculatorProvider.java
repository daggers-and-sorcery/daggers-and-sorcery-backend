package com.morethanheroic.swords.attribute.service.probe;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.probe.extension.AttributeProbeCalculatorExtension;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AttributeProbeCalculatorProvider {

    private final Map<Attribute, AttributeProbeCalculatorExtension> calculatorExtensionMap;

    public AttributeProbeCalculatorProvider(final List<AttributeProbeCalculatorExtension> calculatorExtensions) {
        final Map<Attribute, AttributeProbeCalculatorExtension> temporaryCalculatorExtensionMap = new HashMap<>();
        for (AttributeProbeCalculatorExtension attributeProbeCalculator : calculatorExtensions) {
            temporaryCalculatorExtensionMap.put(attributeProbeCalculator.supportedAttribute(), attributeProbeCalculator);
        }
        calculatorExtensionMap = Collections.unmodifiableMap(temporaryCalculatorExtensionMap);
    }

    public Optional<AttributeProbeCalculatorExtension> getCalculatorExtension(final Attribute attribute) {
        final AttributeProbeCalculatorExtension calculatorExtension = calculatorExtensionMap.get(attribute);

        if (calculatorExtension != null) {
            return Optional.of(calculatorExtension);
        }

        return Optional.empty();
    }
}
