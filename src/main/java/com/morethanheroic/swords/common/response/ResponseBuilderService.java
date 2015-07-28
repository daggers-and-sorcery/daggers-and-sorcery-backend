package com.morethanheroic.swords.common.response;

import com.morethanheroic.swords.user.domain.UserEntity;

public interface ResponseBuilderService {

    Response build(UserEntity user);
}
