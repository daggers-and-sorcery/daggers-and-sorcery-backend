package com.morethanheroic.swords.attribute.service.probe.extension;

import com.morethanheroic.swords.attribute.domain.Attribute;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AttributeProbeCalculatorExtensionResultFactoryProvider {

    private final Map<Attribute, AttributeProbeCalculatorExtensionResultFactory> resultFactoryMap;

    public AttributeProbeCalculatorExtensionResultFactoryProvider(final List<AttributeProbeCalculatorExtensionResultFactory> resultFactoryList) {
        final Map<Attribute, AttributeProbeCalculatorExtensionResultFactory> temporaryResultFactoryMap = new HashMap<>();
        for (AttributeProbeCalculatorExtensionResultFactory attributeProbeCalculator : resultFactoryList) {
            temporaryResultFactoryMap.put(attributeProbeCalculator.supportedAttribute(), attributeProbeCalculator);
        }
        resultFactoryMap = Collections.unmodifiableMap(temporaryResultFactoryMap);
    }

    public Optional<AttributeProbeCalculatorExtensionResultFactory> getResultFactory(final Attribute attribute) {
        final AttributeProbeCalculatorExtensionResultFactory resultFactory = resultFactoryMap.get(attribute);

        if (resultFactory != null) {
            return Optional.of(resultFactory);
        }

        return Optional.empty();
    }
}
