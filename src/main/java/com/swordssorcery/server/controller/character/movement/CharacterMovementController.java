package com.swordssorcery.server.controller.character.movement;

import com.swordssorcery.server.controller.character.movement.request.MovementRequest;
import com.swordssorcery.server.game.movement.MovementManager;
import com.swordssorcery.server.game.user.UserManager;
import com.swordssorcery.server.model.entity.user.UserEntity;
import com.swordssorcery.server.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterMovementController {

    @Autowired
    private UserManager userManager;

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

        userManager.saveUser(user);

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
