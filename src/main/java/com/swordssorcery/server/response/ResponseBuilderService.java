package com.swordssorcery.server.response;

import com.swordssorcery.server.model.User;

public interface ResponseBuilderService {

    Response build(User user);
}
