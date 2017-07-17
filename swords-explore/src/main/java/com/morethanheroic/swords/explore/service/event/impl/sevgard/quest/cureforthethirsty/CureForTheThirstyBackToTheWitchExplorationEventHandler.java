package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest.cureforthethirsty;

import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class CureForTheThirstyBackToTheWitchExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 32;

    private static final int STARTER_STAGE = 0;
    private static final int COMBAT_STAGE = 1;

    private static final int WITCH_ID = 1113;

    private static final int CURE_FOR_THE_THIRSTY_QUEST_ID = 2;
    private static final int CURE_FOR_THE_THIRSTY_QUEST_COMBAT_WITH_THE_WITCH_STAGE_ID = 7;
    private static final int CURE_FOR_THE_THIRSTY_QUEST_NEXT_STAGE_ID = 8;

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
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_24")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_25")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_26")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_27")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_28")
                                .newCombatEntry(WITCH_ID, questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID), CURE_FOR_THE_THIRSTY_QUEST_COMBAT_WITH_THE_WITCH_STAGE_ID)
                                .build()
                )
                .addStage(COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newIsCombatRunningMultiWayPath(explorationContext, CombatType.QUEST_2)
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_28")
                                                .continueCombatEntry(CombatType.QUEST_2)
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_29")
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_30")
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_31")
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID), CURE_FOR_THE_THIRSTY_QUEST_NEXT_STAGE_ID)
                                                .newContinueQuestEntry(questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID))
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
    public int getId() {
        return EVENT_ID;
    }
}
