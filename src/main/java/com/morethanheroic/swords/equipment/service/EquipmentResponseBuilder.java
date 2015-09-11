package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentResponseBuilder {

    public static final boolean SUCCESSFULL_REQUEST = true;
    public static final boolean UNSUCCESSFULL_REQUEST = false;

    private final ResponseFactory responseFactory;

    @Autowired
    public EquipmentResponseBuilder(ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    public Response build(UserEntity user, boolean result) {
        Response response = responseFactory.newResponse(user);
        response.setData("success", result);

        return response;
    }
}
