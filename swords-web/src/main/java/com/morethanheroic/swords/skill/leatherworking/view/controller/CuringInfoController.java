package com.morethanheroic.swords.skill.leatherworking.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.service.response.LeatherworkingCuringInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CuringInfoController {

    @Autowired
    private LeatherworkingCuringInfoResponseBuilder leatherworkingCuringInfoResponseBuilder;

    @RequestMapping(value = "/skill/leatherworking/curing/info", method = RequestMethod.GET)
    public Response scavengingInfo(UserEntity userEntity) {
        return leatherworkingCuringInfoResponseBuilder.build(
                CookingInfoResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .build()
        );
    }
}
