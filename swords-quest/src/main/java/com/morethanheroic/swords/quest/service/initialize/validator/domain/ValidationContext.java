package com.morethanheroic.swords.quest.service.initialize.validator.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ValidationContext {

    private final UserEntity userEntity;
}
