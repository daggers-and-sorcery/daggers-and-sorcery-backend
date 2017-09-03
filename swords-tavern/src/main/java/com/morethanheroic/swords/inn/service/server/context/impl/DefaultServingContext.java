package com.morethanheroic.swords.inn.service.server.context.impl;

import com.morethanheroic.swords.inn.service.server.context.ServingContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DefaultServingContext implements ServingContext {

    private final UserEntity userEntity;
}
