package com.morethanheroic.swords.skill.leatherworking.view.controller.tanning;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.tanning.TanningInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.view.response.service.tanning.TanningInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TanningInfoController {

    @Autowired
    private TanningInfoResponseBuilder tanningInfoResponseBuilder;

    @RequestMapping(value = "/skill/leatherworking/tanning/info", method = RequestMethod.GET)
    public Response curingInfo(UserEntity userEntity) {
        return tanningInfoResponseBuilder.build(
                TanningInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
