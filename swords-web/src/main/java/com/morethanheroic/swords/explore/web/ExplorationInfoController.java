package com.morethanheroic.swords.explore.web;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.ExplorationInfoGatherer;
import com.morethanheroic.swords.explore.service.response.ExplorationResponseBuilder;
import com.morethanheroic.swords.explore.service.response.domain.ExplorationResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExplorationInfoController {

    @Autowired
    private ExplorationInfoGatherer explorationInfoGatherer;

    @Autowired
    private ExplorationResponseBuilder explorationResponseBuilder;

    //TODO: This should write the possible exploration entries (map types where the user can explore) also.
    @RequestMapping(value = "/explore/info", method = RequestMethod.GET)
    public Response exploreInfo(UserEntity userEntity, SessionEntity sessionEntity) {
        final ExplorationResult explorationResult = explorationInfoGatherer.info(userEntity, sessionEntity);

        return explorationResponseBuilder.build(ExplorationResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .explorationEventEntryResults(explorationResult)
                .build()
        );
    }
}
