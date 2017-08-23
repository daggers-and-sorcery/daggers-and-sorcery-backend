package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest.smugglersheaven;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationAssignmentContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.condition.impl.ItemCondition;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class HealpingAldrezExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 39;

    private static final int HEALING_POTION_ID = 123333;

    private static final int SMUGGLERS_HEAVEN_QUEST_ID = 3;
    private static final int SMUGGLERS_HEAVEN_QUEST_NEXT_STAGE_ID = 10;

    private static final int STARTER_STAGE = 0;

    private final ItemDefinitionCache itemDefinitionCache;
    private final QuestDefinitionCache questDefinitionCache;
    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        throw new IllegalStateException("Handle explore called unexpectedly under quest: " + questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID).getName());
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newConditionalMultiWayPath(explorationContext,
                                        Lists.newArrayList(
                                                ItemCondition.builder()
                                                        .itemDefinition(itemDefinitionCache.getDefinition(HEALING_POTION_ID))
                                                        .amount(1)
                                                        .build()
                                        )
                                )
                                .isFailure(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_29")
                                                .build()
                                )
                                .isSuccess(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_30")
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_31")
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID), SMUGGLERS_HEAVEN_QUEST_NEXT_STAGE_ID)
                                                .newFinishQuestEntry(questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID))
                                                .build()
                                )
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return true;
    }

    @Override
    public boolean shouldAssign(ExplorationAssignmentContext explorationAssignmentContext) {
        return false;
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }
}
