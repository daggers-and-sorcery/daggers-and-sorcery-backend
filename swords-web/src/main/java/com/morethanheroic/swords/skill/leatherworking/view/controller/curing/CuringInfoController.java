package com.morethanheroic.swords.skill.leatherworking.view.controller.curing;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.service.EventRegistry;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.curing.CuringInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.view.response.service.curing.CuringInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CuringInfoController {

    private final CuringInfoResponseBuilder curingInfoResponseBuilder;
    private final EventRegistry eventRegistry;

    @GetMapping(value = "/skill/leatherworking/curing/info")
    public Response curingInfo(UserEntity userEntity) {
        return curingInfoResponseBuilder.build(
                CuringInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .eventEntities(eventRegistry.getRegisteredEvents(userEntity, EventType.LEATHERWORKING_CURING))
                        .build()
        );
    }
}
