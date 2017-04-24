package com.morethanheroic.swords.skill.scavenging.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.scavenging.service.ScavengingEntityFactory;
import com.morethanheroic.swords.skill.scavenging.view.response.domain.ScavengingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.scavenging.view.response.service.ScavengingInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScavengingInfoController {

    private final ScavengingInfoResponseBuilder scavengingInfoResponseBuilder;
    private final ScavengingEntityFactory scavengingEntityFactory;

    @GetMapping(value = "/skill/scavenging/info")
    public Response scavengingInfo(UserEntity userEntity) {
        return scavengingInfoResponseBuilder.build(
                ScavengingInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .scavengingEntity(scavengingEntityFactory.getEntity(userEntity))
                        .build()
        );
    }
}
