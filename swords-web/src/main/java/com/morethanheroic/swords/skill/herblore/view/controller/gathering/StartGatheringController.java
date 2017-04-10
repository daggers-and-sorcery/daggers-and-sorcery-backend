package com.morethanheroic.swords.skill.herblore.view.controller.gathering;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.herblore.service.gathering.GatheringService;
import com.morethanheroic.swords.skill.herblore.service.gathering.domain.GatheringResult;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering.GatheringStartResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.view.response.service.gathering.GatheringStartResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StartGatheringController {

    private final GatheringService gatheringService;
    private final GatheringStartResponseBuilder gatheringStartResponseBuilder;

    @PostMapping("/skill/herblore/gathering/start")
    public Response startCuring(final UserEntity userEntity) {
        final GatheringResult result = gatheringService.gather(userEntity);

        return gatheringStartResponseBuilder.build(
                GatheringStartResponseBuilderConfiguration.builder()
                        .result(result)
                        .build()
        );
    }
}
