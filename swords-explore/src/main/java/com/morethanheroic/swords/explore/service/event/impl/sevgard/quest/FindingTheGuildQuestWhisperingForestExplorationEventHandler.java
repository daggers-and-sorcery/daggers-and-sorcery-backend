package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class FindingTheGuildQuestWhisperingForestExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 28;

    private static final int STARTER_STAGE = 0;
    private static final int COMBAT_STAGE = 1;

    private static final int ROPE_ID = 119;
    private static final int CHEST_LOOT_ID = 8;
    private static final int GOBLIN_CHAMPION_ID = 24;

    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_ID = 1;
    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_GOBLIN_CHAMPION_COMBAT_STAGE_ID = 5;
    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID = 6;

    private final QuestDefinitionCache questDefinitionCache;
    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newLootEntry(CHEST_LOOT_ID, "FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_27")
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_28")
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_29")
                                .newUpdateQuestStage(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID)
                                .newContinueQuestEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID))
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_22")
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_23")
                                .newHasItemMultiWayPath(explorationContext, itemDefinitionCache.getDefinition(ROPE_ID))
                                .isSuccess(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_25")
                                                .newRemoveItemEntry(itemDefinitionCache.getDefinition(ROPE_ID))
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_26")
                                                .newCombatEntry(GOBLIN_CHAMPION_ID, questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), WITCHHUNTER_GUILD_JOIN_QUEST_GOBLIN_CHAMPION_COMBAT_STAGE_ID)
                                                .build()

                                )
                                .isFailure(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_24")
                                                .build()
                                )
                                .build()
                )
                .addStage(COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_26")
                                .continueCombatEntry()
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
