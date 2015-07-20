package com.swordssorcery.server.response;

import com.swordssorcery.server.model.entity.user.UserEntity;

public interface ResponseBuilderService {

    Response build(UserEntity user);
}
