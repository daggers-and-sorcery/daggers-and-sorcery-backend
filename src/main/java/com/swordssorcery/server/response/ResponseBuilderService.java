package com.swordssorcery.server.response;

import com.swordssorcery.server.model.db.User;

public interface ResponseBuilderService {

    Response build(User user);
}
