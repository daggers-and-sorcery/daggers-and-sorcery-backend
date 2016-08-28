package com.morethanheroic.swords.attribute.service.probe.extension.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PreProbeHookData {

    private final AttributeProbeCalculatorExtensionResult extensionResult;
}
