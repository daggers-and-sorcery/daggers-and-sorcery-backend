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
public class BackToTheQueenExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 38;

    private static final int COIN_OF_DRAKKAR_ID = 213;
    private static final int COIN_OF_DRAKKAR_AMOUNT_REQUIRED = 10;

    private static final int SMUGGLERS_HEAVEN_QUEST_ID = 3;
    private static final int SMUGGLERS_HEAVEN_QUEST_NEXT_STAGE_ID = 9;

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
                                                        .itemDefinition(itemDefinitionCache.getDefinition(COIN_OF_DRAKKAR_ID))
                                                        .amount(COIN_OF_DRAKKAR_AMOUNT_REQUIRED)
                                                        .build()
                                        )
                                )
                                .isFailure(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_32")
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_33")
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_34")
                                                .build()
                                )
                                .isSuccess(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_24")
                                                .newRemoveItemEntry(itemDefinitionCache.getDefinition(COIN_OF_DRAKKAR_ID), COIN_OF_DRAKKAR_AMOUNT_REQUIRED)
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_26")
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_27")
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID), SMUGGLERS_HEAVEN_QUEST_NEXT_STAGE_ID)
                                                .newContinueQuestEntry(questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID))
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