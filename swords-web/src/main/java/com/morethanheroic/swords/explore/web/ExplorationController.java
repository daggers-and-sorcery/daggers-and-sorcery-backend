package com.morethanheroic.swords.explore.web;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.ExplorationExecutor;
import com.morethanheroic.swords.explore.service.response.ExplorationResponseBuilder;
import com.morethanheroic.swords.explore.service.response.domain.ExplorationResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExplorationController {

    @Autowired
    private ExplorationExecutor explorationEventExecutor;

    @Autowired
    private ExplorationResponseBuilder explorationResponseBuilder;

    @RequestMapping(value = "/explore/", method = RequestMethod.GET)
    public Response explore(UserEntity userEntity, SessionEntity sessionEntity) {
        final ExplorationResult explorationResult = explorationEventExecutor.explore(userEntity, sessionEntity);

        return explorationResponseBuilder.build(ExplorationResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .explorationEventEntryResults(explorationResult)
                .build()
        );
    }

    @RequestMapping(value = "/explore/{state}", method = RequestMethod.GET)
    public Response explore(UserEntity userEntity, SessionEntity sessionEntity, @RequestParam int state) {
        final ExplorationResult explorationResult = explorationEventExecutor.explore(userEntity, sessionEntity, state);

        return explorationResponseBuilder.build(ExplorationResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .explorationEventEntryResults(explorationResult)
                .build()
        );
    }
}
