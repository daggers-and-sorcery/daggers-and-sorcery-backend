package com.morethanheroic.swords.attribute.service.probe.extension.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostProbeHookData {

    private final boolean successfulProbe;
    private final AttributeProbeCalculatorExtensionResult extensionResult;
}
