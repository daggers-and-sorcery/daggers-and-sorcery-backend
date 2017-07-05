package com.morethanheroic.swords.explore.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.ExplorationEventInfoGatherer;
import com.morethanheroic.swords.explore.view.response.ExplorationResponseBuilder;
import com.morethanheroic.swords.explore.view.response.domain.ExplorationResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExplorationInfoController {

    private final ExplorationEventInfoGatherer explorationEventInfoGatherer;
    private final ExplorationResponseBuilder explorationResponseBuilder;

    @GetMapping(value = "/explore/info")
    public Response exploreInfo(UserEntity userEntity, SessionEntity sessionEntity) {
        final ExplorationResult explorationResult = explorationEventInfoGatherer.info(userEntity, sessionEntity);

        return explorationResponseBuilder.build(ExplorationResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .explorationEventEntryResults(explorationResult)
                .build()
        );
    }
}
