package com.morethanheroic.swords.attribute.service.probe.extension.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RequirementsData {

    private final UserEntity userEntity;
    private final AttributeProbeCalculatorExtensionResult extensionResult;
}
