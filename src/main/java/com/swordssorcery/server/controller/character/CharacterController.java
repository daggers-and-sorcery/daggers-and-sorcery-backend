package com.swordssorcery.server.controller.character;

import com.swordssorcery.server.model.db.User;
import com.swordssorcery.server.response.Response;
import com.swordssorcery.server.response.character.CharacterInfoResponseBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CharacterController {

    @Autowired
    private CharacterInfoResponseBuilderService characterInfoResponseBuilderService;

    @ResponseBody
    @RequestMapping(value = "/character/info", method = RequestMethod.GET)
    public Response info(User user) {
        return characterInfoResponseBuilderService.build(user);
    }
}
