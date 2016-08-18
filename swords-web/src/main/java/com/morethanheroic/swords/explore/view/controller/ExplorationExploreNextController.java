package com.morethanheroic.swords.explore.view.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.ExplorationEventExplorer;
import com.morethanheroic.swords.explore.view.response.ExplorationResponseBuilder;
import com.morethanheroic.swords.explore.view.response.domain.ExplorationResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExplorationExploreNextController {

    private final ExplorationEventExplorer explorationEventExecutor;
    private final ExplorationResponseBuilder explorationResponseBuilder;

    @RequestMapping(value = "/explore/next", method = RequestMethod.GET)
    public Response explore(UserEntity userEntity, SessionEntity sessionEntity) {
        final ExplorationResult explorationResult = explorationEventExecutor.exploreNext(userEntity, sessionEntity);

        return explorationResponseBuilder.build(ExplorationResponseBuilderConfiguration.builder()
               .userEntity(userEntity)
               .explorationEventEntryResults(explorationResult)
               .build()
        );
    }
}
