package com.swordssorcery.server.controller.character.profile;

import com.swordssorcery.server.controller.character.profile.response.CharacterInfoResponseBuilderService;
import com.swordssorcery.server.model.entity.user.UserEntity;
import com.swordssorcery.server.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterProfileController {

    @Autowired
    private CharacterInfoResponseBuilderService characterInfoResponseBuilderService;

    @RequestMapping(value = "/character/info", method = RequestMethod.GET)
    public Response info(UserEntity user) {
        return characterInfoResponseBuilderService.build(user);
    }
}
