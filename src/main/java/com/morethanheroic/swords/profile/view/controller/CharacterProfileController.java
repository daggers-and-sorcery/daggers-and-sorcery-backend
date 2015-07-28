package com.morethanheroic.swords.profile.view.controller;

import com.morethanheroic.swords.profile.service.CharacterInfoResponseBuilderService;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.common.response.Response;
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
