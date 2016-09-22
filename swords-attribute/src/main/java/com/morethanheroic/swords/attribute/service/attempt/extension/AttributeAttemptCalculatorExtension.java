package com.morethanheroic.swords.attribute.service.attempt.extension;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.AttributeAttemptCalculatorExtensionResult;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.PostAttemptHookData;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.PreAttemptHookData;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface AttributeAttemptCalculatorExtension<T extends AttributeAttemptCalculatorExtensionResult> {

    boolean checkRequirements(final T extensionResult, final UserEntity userEntity);

    void preProbeHook(final T extensionResult, final PreAttemptHookData preAttemptHookData);

    void postProbeHook(final T extensionResult, final PostAttemptHookData postAttemptHookData);

    Attribute supportedAttribute();
}
