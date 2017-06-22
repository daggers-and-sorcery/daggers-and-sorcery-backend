package com.morethanheroic.swords.explore.view.controller.quest;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.quest.QuestExplorationEventExplorer;
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
public class QuestExplorationExploreController {

    private final QuestExplorationEventExplorer questExplorationEventExplorer;
    private final ExplorationResponseBuilder explorationResponseBuilder;

    @GetMapping("/explore/quest/{questId}")
    public Response explore(final UserEntity userEntity, final @PathVariable("questId") QuestDefinition questDefinition) {
        final ExplorationResult explorationResult = questExplorationEventExplorer.exploreNextStage(userEntity, questDefinition);

        return explorationResponseBuilder.build(
                ExplorationResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .explorationEventEntryResults(explorationResult)
                        .build()
        );
    }

    @GetMapping("/explore/quest/{questId}/{stage}")
    public Response explore(final UserEntity userEntity, final @PathVariable("questId") QuestDefinition questDefinition, final @PathVariable int stage) {
        final ExplorationResult explorationResult = questExplorationEventExplorer.exploreStage(userEntity, questDefinition, stage);

        return explorationResponseBuilder.build(
                ExplorationResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .explorationEventEntryResults(explorationResult)
                        .build()
        );
    }
}
