package com.morethanheroic.swords.spell.service.response;

import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CastSpellResponseBuilder {

    private final ResponseFactory responseFactory;

    @Autowired
    public CastSpellResponseBuilder(ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    public Response build(UserEntity userEntity, boolean success) {
        Response response = responseFactory.newResponse(userEntity);

        response.setData("success", success);

        return response;
    }
}
