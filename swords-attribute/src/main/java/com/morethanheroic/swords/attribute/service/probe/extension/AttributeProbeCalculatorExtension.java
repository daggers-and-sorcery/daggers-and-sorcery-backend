package com.morethanheroic.swords.attribute.service.probe.extension;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PostProbeHookData;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PreProbeHookData;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.RequirementsData;

public interface AttributeProbeCalculatorExtension {

    boolean checkRequirements(final RequirementsData requirementsData);

    void preProbeHook(final PreProbeHookData preProbeHookData);

    void postProbeHook(final PostProbeHookData postProbeHookData);

    Attribute supportedAttribute();
}
