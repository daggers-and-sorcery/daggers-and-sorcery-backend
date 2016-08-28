package com.morethanheroic.swords.attribute.service.probe.extension.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PreProbeHookData {

    private final UserEntity userEntity;
}
