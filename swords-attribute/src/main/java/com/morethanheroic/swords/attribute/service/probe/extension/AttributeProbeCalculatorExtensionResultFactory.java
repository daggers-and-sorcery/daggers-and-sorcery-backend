package com.morethanheroic.swords.attribute.service.probe.extension;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.AttributeProbeCalculatorExtensionResult;

public interface AttributeProbeCalculatorExtensionResultFactory {

    AttributeProbeCalculatorExtensionResult newExtensionResult();

    Attribute supportedAttribute();
}
