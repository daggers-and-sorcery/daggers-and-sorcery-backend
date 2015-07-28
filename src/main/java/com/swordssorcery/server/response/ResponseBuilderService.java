package com.swordssorcery.server.response;

import com.morethanheroic.swords.user.domain.UserEntity;

public interface ResponseBuilderService {

    Response build(UserEntity user);
}
