package com.morethanheroic.swords.profile.view.controller;

import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.profile.service.ProfileInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterProfileController {

    private final ProfileInfoResponseBuilder profileInfoResponseBuilder;

    @Autowired
    public CharacterProfileController(ProfileInfoResponseBuilder profileInfoResponseBuilder) {
        this.profileInfoResponseBuilder = profileInfoResponseBuilder;
    }

    @RequestMapping(value = "/character/info", method = RequestMethod.GET)
    public Response info(UserEntity user) {
        return profileInfoResponseBuilder.build(user);
    }
}
