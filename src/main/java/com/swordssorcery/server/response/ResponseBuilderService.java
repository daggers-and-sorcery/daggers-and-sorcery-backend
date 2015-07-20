package com.swordssorcery.server.response;

import com.swordssorcery.server.model.db.user.UserDatabaseEntity;

public interface ResponseBuilderService {

    Response build(UserDatabaseEntity user);
}
