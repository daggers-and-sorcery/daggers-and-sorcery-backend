package com.morethanheroic.swords.settings.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveOtherSettingsResponseBuilder {

    @Autowired
    private ResponseFactory responseFactory;

    public Response build(UserEntity userEntity, String result) {
        Response response = responseFactory.newResponse(userEntity);

        response.setData("result", result);

        return response;
    }
}

