package com.morethanheroic.swords.settings.service;

import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveOtherSettingsResponseBuilder {

    @Autowired
    private ResponseFactory responseFactory;

    public CharacterRefreshResponse build(UserEntity userEntity, String result) {
        CharacterRefreshResponse response = responseFactory.newResponse(userEntity);

        response.setData("result", result);

        return response;
    }
}

