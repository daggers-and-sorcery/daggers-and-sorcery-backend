package com.morethanheroic.swords.attribute.service.probe.extension;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.AttributeProbeCalculatorExtensionResult;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PostProbeHookData;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PreProbeHookData;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface AttributeProbeCalculatorExtension<T extends AttributeProbeCalculatorExtensionResult> {

    boolean checkRequirements(final T extensionResult, final UserEntity userEntity);

    void preProbeHook(final T extensionResult, final PreProbeHookData preProbeHookData);

    void postProbeHook(final T extensionResult, final PostProbeHookData postProbeHookData);

    Attribute supportedAttribute();
}
