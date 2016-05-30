package com.morethanheroic.swords.skill.leatherworking.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.service.EventRegistry;
import com.morethanheroic.swords.skill.leatherworking.service.response.CuringInfoResponseBuilder;
import com.morethanheroic.swords.skill.leatherworking.service.response.domain.configuration.CuringInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CuringInfoController {

    @Autowired
    private CuringInfoResponseBuilder curingInfoResponseBuilder;

    @Autowired
    private EventRegistry eventRegistry;

    @RequestMapping(value = "/skill/leatherworking/curing/info", method = RequestMethod.GET)
    public Response curingInfo(UserEntity userEntity) {
        return curingInfoResponseBuilder.build(
                CuringInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .eventEntities(eventRegistry.getRegisteredEvents(userEntity, EventType.LEATHERWORKING_CURING))
                        .build()
        );
    }
}
