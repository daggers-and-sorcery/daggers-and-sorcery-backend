package com.morethanheroic.swords.explore.service.quest;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.service.cache.ExplorationEventHandlerCache;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventHandler;
import com.morethanheroic.swords.quest.domain.QuestEntity;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.domain.definition.QuestStateDefinition;
import com.morethanheroic.swords.quest.service.entity.QuestEntityFactory;
import com.morethanheroic.swords.quest.service.entity.domain.QuestEntityFactoryContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestExplorationEventInfoGatherer {

    private final QuestEntityFactory questEntityFactory;
    private final ExplorationEventHandlerCache explorationEventHandlerCache;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ExplorationResult info(final UserEntity userEntity, final QuestDefinition questDefinition) {
        return buildSuccessfulInfoResult(userEntity, buildExplorationContextForQuest(userEntity, questDefinition));
    }

    private ExplorationResult buildSuccessfulInfoResult(final UserEntity userEntity, final ExplorationContext explorationContext) {
        return ((MultiStageExplorationEventHandler) explorationContext.getEvent()).info(userEntity, explorationContext.getStage());
    }

    private ExplorationContext buildExplorationContextForQuest(final UserEntity userEntity, final QuestDefinition questDefinition) {
        final QuestEntity questEntity = questEntityFactory.getEntity(
                QuestEntityFactoryContext.builder()
                        .userEntity(userEntity)
                        .questDefinition(questDefinition)
                        .build()
        );

        final QuestStateDefinition activeQuestStage = questEntity.getDefinitionForActiveStage();

        return ExplorationContext.builder()
                .event(explorationEventHandlerCache.getHandler(activeQuestStage.getEventId()))
                .stage(activeQuestStage.getEventStage())
                .build();
    }
}
