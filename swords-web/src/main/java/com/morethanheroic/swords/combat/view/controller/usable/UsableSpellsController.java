package com.morethanheroic.swords.combat.view.controller.usable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.combat.view.response.service.usable.UsableSpellsResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;

@RestController
public class UsableSpellsController {

    @Autowired
    private UsableSpellsResponseBuilder usableSpellsResponseBuilder;

    @RequestMapping(value = "/combat/usable/spell", method = RequestMethod.GET)
    public Response usableSpells(UserEntity userEntity) {
        return usableSpellsResponseBuilder.build(userEntity);
    }
}
