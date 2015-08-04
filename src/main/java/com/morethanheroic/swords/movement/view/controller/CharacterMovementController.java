package com.morethanheroic.swords.movement.view.controller;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.movement.service.MovementManager;
import com.morethanheroic.swords.movement.service.MovementResponseBuilder;
import com.morethanheroic.swords.movement.service.PositionResponseBuilder;
import com.morethanheroic.swords.movement.view.request.MovementRequest;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
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

    @Autowired
    private PositionResponseBuilder positionResponseBuilder;

    @Autowired
    private MovementResponseBuilder movementResponseBuilder;

    @RequestMapping(value = "/character/move", method = RequestMethod.POST)
    public Response move(UserEntity user, @RequestBody MovementRequest direction) {
        boolean result = movementManager.move(user, direction.getDirection());

        //TODO: combat here!

        return movementResponseBuilder.build(user, result);
    }

    @RequestMapping(value = "/character/position", method = RequestMethod.GET)
    public Response position(UserEntity user) {
        return positionResponseBuilder.build(user);
    }
}
