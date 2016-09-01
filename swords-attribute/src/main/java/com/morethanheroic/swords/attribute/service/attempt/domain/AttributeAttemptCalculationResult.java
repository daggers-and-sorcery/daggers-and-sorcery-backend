package com.morethanheroic.swords.attribute.service.attempt.domain;

import com.morethanheroic.swords.attribute.service.attempt.extension.domain.AttributeAttemptCalculatorExtensionResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AttributeAttemptCalculationResult {

    private final boolean successful;
    private final int rolledValue;
    private final AttributeAttemptCalculatorExtensionResult extensionResult;
}
