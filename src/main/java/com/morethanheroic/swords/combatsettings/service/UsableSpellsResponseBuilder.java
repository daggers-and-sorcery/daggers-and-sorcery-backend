package com.morethanheroic.swords.combatsettings.service;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsableSpellsResponseBuilder {

    private final ResponseFactory responseFactory;

    @Autowired
    public UsableSpellsResponseBuilder(ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    public Response build(UserEntity userEntity) {
        Response response =  responseFactory.newResponse(userEntity);

        //TODO: fill this after we added spells
        response.setData("spellList", new ArrayList<>());

        return response;
    }
}
