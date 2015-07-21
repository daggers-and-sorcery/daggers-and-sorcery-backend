package com.swordssorcery.server.controller.character.movement;

import com.swordssorcery.server.controller.character.movement.request.MovementRequest;
import com.swordssorcery.server.model.entity.user.UserEntity;
import com.swordssorcery.server.response.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterMovementController {

    @RequestMapping(value = "/character/move", method = RequestMethod.POST)
    public Response move(UserEntity user, @RequestBody MovementRequest direction) {
        Response response = new Response();
        response.setData("success", true);

        return response;
    }
}
