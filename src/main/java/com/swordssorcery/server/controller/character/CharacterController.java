package com.swordssorcery.server.controller.character;

import com.swordssorcery.server.controller.character.request.MovementRequest;
import com.swordssorcery.server.model.entity.user.UserEntity;
import com.swordssorcery.server.response.Response;
import com.swordssorcery.server.response.character.CharacterInfoResponseBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    @Autowired
    private CharacterInfoResponseBuilderService characterInfoResponseBuilderService;

    @RequestMapping(value = "/character/info", method = RequestMethod.GET)
    public Response info(UserEntity user) {
        return characterInfoResponseBuilderService.build(user);
    }

    @RequestMapping(value = "/character/move", method = RequestMethod.POST)
    public Response move(UserEntity user, @RequestBody MovementRequest direction) {
        Response response = new Response();
        response.setData("success", true);

        return response;
    }
}
