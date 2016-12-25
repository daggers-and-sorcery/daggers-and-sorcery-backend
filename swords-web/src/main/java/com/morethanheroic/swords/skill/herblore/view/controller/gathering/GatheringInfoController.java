package com.morethanheroic.swords.skill.herblore.view.controller.gathering;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.service.EventRegistry;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering.GatheringInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.view.response.service.gathering.GatheringInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GatheringInfoController {

    private final GatheringInfoResponseBuilder gatheringInfoResponseBuilder;
    private final EventRegistry eventRegistry;

    @GetMapping(value = "/skill/herblore/gathering/info")
    public Response curingInfo(UserEntity userEntity) {
        return gatheringInfoResponseBuilder.build(
                GatheringInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .eventEntities(eventRegistry.getRegisteredEvents(userEntity, EventType.HERBLORE_GATHERING))
                        .build()
        );
    }
}
