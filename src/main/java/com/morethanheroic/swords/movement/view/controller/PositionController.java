package com.morethanheroic.swords.movement.view.controller;

import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.movement.service.PositionResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {

    private final PositionResponseBuilder positionResponseBuilder;

    @Autowired
    public PositionController(PositionResponseBuilder positionResponseBuilder) {
        this.positionResponseBuilder = positionResponseBuilder;
    }

    @RequestMapping(value = "/character/position", method = RequestMethod.GET)
    public Response position(UserEntity user) {
        return positionResponseBuilder.build(user);
    }
}
