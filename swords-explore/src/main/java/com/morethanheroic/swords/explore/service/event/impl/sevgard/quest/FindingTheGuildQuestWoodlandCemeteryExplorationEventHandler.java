package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest;

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
public class FindingTheGuildQuestWoodlandCemeteryExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 29;

    private static final int STARTER_STAGE = 0;
    private static final int COMBAT_STAGE = 1;

    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_ID = 1;
    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_KIAN_COMBAT_STAGE_ID = 7;
    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID = 8;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final QuestDefinitionCache questDefinitionCache;

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_29")
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_30")
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_31")
                                //TODO: Combat
                                //TODO: Kian, the Forsaken
                                .newCombatEntry(11, questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), WITCHHUNTER_GUILD_JOIN_QUEST_KIAN_COMBAT_STAGE_ID)
                                .build()
                )
                .addStage(COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newIsCombatRunningMultiWayPath(explorationContext, CombatType.QUEST_1)
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_31")
                                                .continueCombatEntry(CombatType.QUEST_1)
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_32")
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID)
                                                .newFinishQuestEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID))
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
