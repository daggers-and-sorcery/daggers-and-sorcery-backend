package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest.smugglersheaven;

import com.morethanheroic.swords.combat.domain.CombatType;
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
public class CampOfTheSmugglersExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 37;

    private static final int SMUGGLER_ID = 333;

    private static final int SMUGGLERS_HEAVEN_QUEST_ID = 3;
    private static final int SMUGGLERS_HEAVEN_QUEST_FIRST_COMBAT_STAGE_ID = 6;
    private static final int SMUGGLERS_HEAVEN_QUEST_SECOND_COMBAT_STAGE_ID = 7;
    private static final int SMUGGLERS_HEAVEN_QUEST_NEXT_STAGE_ID = 8;

    private static final int STARTER_STAGE = 0;
    private static final int FIRST_COMBAT_STAGE = 1;
    private static final int SECOND_COMBAT_STAGE = 2;

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
                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_12")
                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_13")
                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_14")
                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_15")
                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_16")
                                .newCombatEntry(SMUGGLER_ID, questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID), SMUGGLERS_HEAVEN_QUEST_FIRST_COMBAT_STAGE_ID)
                                .build()
                )
                .addStage(FIRST_COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newIsCombatRunningMultiWayPath(explorationContext, CombatType.QUEST_3)
                                .isSuccess(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_16")
                                                .continueCombatEntry(CombatType.QUEST_3)
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_17")
                                                .newCombatEntry(SMUGGLER_ID, questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID), SMUGGLERS_HEAVEN_QUEST_SECOND_COMBAT_STAGE_ID)
                                                .build()
                                )
                                .build()
                )
                .addStage(SECOND_COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newIsCombatRunningMultiWayPath(explorationContext, CombatType.QUEST_3)
                                .isSuccess(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_17")
                                                .continueCombatEntry(CombatType.QUEST_3)
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_18")
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_19")
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_20")
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_21")
                                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_22")
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