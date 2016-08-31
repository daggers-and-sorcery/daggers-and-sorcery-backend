package com.morethanheroic.swords.attribute.service.probe.extension;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.AttributeProbeCalculatorExtensionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GlobalAttributeProbeCalculatorExtensionResultFactory {

    private final AttributeProbeCalculatorExtensionResultFactoryProvider attributeProbeCalculatorExtensionResultFactoryProvider;

    public AttributeProbeCalculatorExtensionResult newExtensionResult(final Attribute attribute) {
        final Optional<AttributeProbeCalculatorExtensionResultFactory> resultFactoryOptional = attributeProbeCalculatorExtensionResultFactoryProvider.getResultFactory(attribute);

        if (resultFactoryOptional.isPresent()) {
            return resultFactoryOptional.get().newExtensionResult();
        }

        return null;
    }
}
