package com.morethanheroic.swords.movement.view.controller;

import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.movement.service.MovementManager;
import com.morethanheroic.swords.movement.service.MovementResponseBuilder;
import com.morethanheroic.swords.movement.view.request.MovementRequest;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovementController {

    private final MovementManager movementManager;
    private final MovementResponseBuilder movementResponseBuilder;

    @Autowired
    public MovementController(MovementManager movementManager, MovementResponseBuilder movementResponseBuilder) {
        this.movementManager = movementManager;
        this.movementResponseBuilder = movementResponseBuilder;
    }

    @RequestMapping(value = "/character/move", method = RequestMethod.POST)
    public Response move(UserEntity user, @RequestBody MovementRequest direction) {
        boolean result = movementManager.move(user, direction.getDirection());

        //TODO: combat here!

        return movementResponseBuilder.build(user, result);
    }
}
