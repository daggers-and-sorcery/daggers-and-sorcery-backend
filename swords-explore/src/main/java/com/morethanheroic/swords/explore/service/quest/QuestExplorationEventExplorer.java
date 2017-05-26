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

/**
 * An exploration event service that run an {@link com.morethanheroic.swords.explore.service.event.ExplorationEvent} stage for a quest.
 */
@Service
@RequiredArgsConstructor
public class QuestExplorationEventExplorer {

    private final QuestEntityFactory questEntityFactory;
    private final ExplorationEventHandlerCache explorationEventHandlerCache;

    public ExplorationResult exploreNextStage(final UserEntity userEntity, final QuestDefinition questDefinition) {
        if (!canExplore(userEntity, questDefinition)) {
            throw new IllegalStateException("The user trying to complete a quest he's not started or already completed!");
        }

        return buildSuccessfulExplorationResult(userEntity, buildExplorationContextForQuest(userEntity, questDefinition));
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

    private ExplorationResult buildSuccessfulExplorationResult(final UserEntity userEntity, final ExplorationContext explorationContext) {
        return ((MultiStageExplorationEventHandler) explorationContext.getEvent()).explore(userEntity, explorationContext.getStage());
    }

    private boolean canExplore(final UserEntity userEntity, final QuestDefinition questDefinition) {
        final QuestEntity questEntity = questEntityFactory.getEntity(
                QuestEntityFactoryContext.builder()
                        .userEntity(userEntity)
                        .questDefinition(questDefinition)
                        .build()
        );

        if (!questEntity.isStarted() || questEntity.isCompleted()) {
            return false;
        }

        final QuestStateDefinition activeQuestStage = questEntity.getDefinitionForActiveStage();

        if (!((MultiStageExplorationEventHandler) explorationEventHandlerCache.getHandler(activeQuestStage.getEventId())).isValidNextStageAtStage(activeQuestStage.getEventStage(), activeQuestStage.getEventStage())) {
            return true;
        }

        return true;
    }
}
