package com.morethanheroic.swords.explore.web;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.ExplorationFacade;
import com.morethanheroic.swords.explore.service.response.ExplorationResponseBuilder;
import com.morethanheroic.swords.explore.service.response.domain.ExplorationResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExplorationController {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ExplorationFacade explorationFacade;

    @Autowired
    private ExplorationResponseBuilder explorationResponseBuilder;

    @RequestMapping(value = "/explore/", method = RequestMethod.GET)
    public Response explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationFacade.explore(userEntity);

        return explorationResponseBuilder.build(ExplorationResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .explorationEventEntryResults(explorationResult)
                .build()
        );
    }
}
