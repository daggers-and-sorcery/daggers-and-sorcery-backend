package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.swords.race.model.Race;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //TODO: move this (or remove?)
    @RequestMapping(value = "/user/race/list", method = RequestMethod.GET)
    public Race[] raceList() {
        return Race.values();
    }

}
