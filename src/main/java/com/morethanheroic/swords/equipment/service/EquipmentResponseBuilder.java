package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.common.response.Response;
import org.springframework.stereotype.Service;

@Service
public class EquipmentResponseBuilder {

    public static final boolean SUCCESSFULL_REQUEST = true;
    public static final boolean UNSUCCESSFULL_REQUEST = false;

    public Response build(boolean result) {
        Response response = new Response();
        response.setData("success", result);

        return response;
    }
}
