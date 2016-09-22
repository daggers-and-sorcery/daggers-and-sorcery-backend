package com.morethanheroic.swords.attribute.service.attempt.extension;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.AttributeAttemptCalculatorExtensionResult;

public interface AttributeAttemptCalculatorExtensionResultFactory {

    AttributeAttemptCalculatorExtensionResult newExtensionResult();

    Attribute supportedAttribute();
}
