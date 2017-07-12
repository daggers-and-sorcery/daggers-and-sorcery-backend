package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationAssignmentContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class CureForTheThirstyApproachingTheWitchExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 30;

    private static final int STARTER_STAGE = 0;

    private static final int CURE_FOR_THE_THIRSTY_QUEST_ID = 1;
    private static final int CURE_FOR_THE_THIRSTY_QUEST_NEXT_STAGE_ID = 2;

    private final QuestDefinitionCache questDefinitionCache;
    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        throw new IllegalStateException("Handle explore called unexpectedly under quest: " + questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID).getName());
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_1")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_2")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_3")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_4")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_5")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_6")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_7")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_8")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_9")
                                .newUpdateQuestStage(questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID), CURE_FOR_THE_THIRSTY_QUEST_NEXT_STAGE_ID)
                                .newContinueQuestEntry(questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID))
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }

    @Override
    public boolean shouldAssign(ExplorationAssignmentContext explorationAssignmentContext) {
        return false;
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return true;
    }
}
