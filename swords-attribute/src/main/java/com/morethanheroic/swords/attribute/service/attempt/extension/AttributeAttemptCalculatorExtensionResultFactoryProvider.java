package com.morethanheroic.swords.attribute.service.attempt.extension;

import com.morethanheroic.swords.attribute.domain.Attribute;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AttributeAttemptCalculatorExtensionResultFactoryProvider {

    private final Map<Attribute, AttributeAttemptCalculatorExtensionResultFactory> resultFactoryMap;

    public AttributeAttemptCalculatorExtensionResultFactoryProvider(final List<AttributeAttemptCalculatorExtensionResultFactory> resultFactoryList) {
        final Map<Attribute, AttributeAttemptCalculatorExtensionResultFactory> temporaryResultFactoryMap = new HashMap<>();
        for (AttributeAttemptCalculatorExtensionResultFactory attributeProbeCalculator : resultFactoryList) {
            temporaryResultFactoryMap.put(attributeProbeCalculator.supportedAttribute(), attributeProbeCalculator);
        }
        resultFactoryMap = Collections.unmodifiableMap(temporaryResultFactoryMap);
    }

    public Optional<AttributeAttemptCalculatorExtensionResultFactory> getResultFactory(final Attribute attribute) {
        final AttributeAttemptCalculatorExtensionResultFactory resultFactory = resultFactoryMap.get(attribute);

        if (resultFactory != null) {
            return Optional.of(resultFactory);
        }

        return Optional.empty();
    }
}
