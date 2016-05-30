package com.morethanheroic.swords.skill.leatherworking.view.controller;

import com.morethanheroic.swords.skill.leatherworking.service.CuringService;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartCuringController {

    @Autowired
    private CuringService curingService;

    @RequestMapping(value = "/skill/leatherworking/curing/start", method = RequestMethod.POST)
    public void startCuring(UserEntity userEntity) {
        curingService.cure(userEntity, 1);
    }
}
