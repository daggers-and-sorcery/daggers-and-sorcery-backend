package com.morethanheroic.swords.explore.view.controller.quest;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.quest.QuestExplorationEventInfoGatherer;
import com.morethanheroic.swords.explore.view.response.ExplorationResponseBuilder;
import com.morethanheroic.swords.explore.view.response.domain.ExplorationResponseBuilderConfiguration;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestExplorationInfoController {

    private final QuestExplorationEventInfoGatherer questExplorationEventInfoGatherer;
    private final ExplorationResponseBuilder explorationResponseBuilder;

    @GetMapping("/explore/quest/info/{questId}")
    public Response exploreInfo(final UserEntity userEntity, @PathVariable("questId") QuestDefinition questDefinition) {
        final ExplorationResult explorationResult = questExplorationEventInfoGatherer.info(userEntity, questDefinition);

        return explorationResponseBuilder.build(ExplorationResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .explorationEventEntryResults(explorationResult)
                .build()
        );
    }
}
