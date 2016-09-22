package com.morethanheroic.swords.attribute.service.attempt.extension;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.AttributeAttemptCalculatorExtensionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GlobalAttributeAttemptCalculatorExtensionResultFactory {

    private final AttributeAttemptCalculatorExtensionResultFactoryProvider attributeAttemptCalculatorExtensionResultFactoryProvider;

    public AttributeAttemptCalculatorExtensionResult newExtensionResult(final Attribute attribute) {
        final Optional<AttributeAttemptCalculatorExtensionResultFactory> resultFactoryOptional = attributeAttemptCalculatorExtensionResultFactoryProvider
            .getResultFactory(attribute);

        if (resultFactoryOptional.isPresent()) {
            return resultFactoryOptional.get().newExtensionResult();
        }

        return null;
    }
}
