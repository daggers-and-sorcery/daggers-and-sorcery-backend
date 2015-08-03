package com.morethanheroic.swords.movement.view.controller;

import com.morethanheroic.swords.movement.view.request.MovementRequest;
import com.morethanheroic.swords.movement.service.MovementManager;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import com.morethanheroic.swords.user.service.UserManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterMovementController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MovementManager movementManager;

    @RequestMapping(value = "/character/move", method = RequestMethod.POST)
    public Response move(UserEntity user, @RequestBody MovementRequest direction) {
        boolean result = movementManager.move(user, direction.getDirection());

        //TODO: combat here!

        Response response = new Response();

        response.setData("success", result);
        response.setData("x", user.getXPosition());
        response.setData("y", user.getYPosition());
        response.setData("map", user.getMap().getId());

        user.setPosition(user.getXPosition(), user.getYPosition());

        return response;
    }

    @RequestMapping(value = "/character/position", method = RequestMethod.GET)
    public Response position(UserEntity user) {
        Response response = new Response();

        response.setData("x", user.getXPosition());
        response.setData("y", user.getYPosition());
        response.setData("map", user.getMap().getId());

        return response;
    }
}
