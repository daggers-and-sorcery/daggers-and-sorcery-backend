package com.morethanheroic.swords.attribute.service.probe.domain;

import com.morethanheroic.swords.attribute.service.probe.extension.domain.AttributeProbeCalculatorExtensionResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AttributeProbeCalculationResult {

    private final boolean successful;
    private final AttributeProbeCalculatorExtensionResult extensionResult;
}
