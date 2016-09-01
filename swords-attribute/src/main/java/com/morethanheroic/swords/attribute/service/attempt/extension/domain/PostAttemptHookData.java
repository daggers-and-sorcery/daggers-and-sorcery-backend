package com.morethanheroic.swords.attribute.service.attempt.extension.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostAttemptHookData {

    private final UserEntity userEntity;
    private final boolean successfulProbe;
}
