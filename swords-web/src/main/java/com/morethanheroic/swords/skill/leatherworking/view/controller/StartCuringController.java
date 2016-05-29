package com.morethanheroic.swords.skill.leatherworking.view.controller;

import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.event.service.EventRegistry;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartCuringController {

    @Autowired
    private EventRegistry eventRegistry;

    @RequestMapping(value = "/skill/leatherworking/curing/start", method = RequestMethod.POST)
    public void startCuring(UserEntity userEntity) {
        //TODO: Check that the user has more places for curing
        //TODO: check that the user has the items
        //TODO: Remove the items
        //TODO: Add the event correctly
        eventRegistry.registerEvent(EventEntity.builder()
                .eventId(1)
                .userId(userEntity.getId())
                .build()
        );
    }
}
