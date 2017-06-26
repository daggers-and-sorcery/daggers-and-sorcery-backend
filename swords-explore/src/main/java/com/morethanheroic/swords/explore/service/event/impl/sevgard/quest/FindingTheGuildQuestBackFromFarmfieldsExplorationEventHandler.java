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
public class FindingTheGuildQuestBackFromFarmfieldsExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 27;

    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_ID = 1;
    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID = 4;

    private static final int STARTER_STAGE = 0;

    private final QuestDefinitionCache questDefinitionCache;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        throw new IllegalStateException("Handle explore called unexpectedly under quest: " + questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID).getName());
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_20")
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_21")
                                .newUpdateQuestStage(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID)
                                .newContinueQuestEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID))
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
