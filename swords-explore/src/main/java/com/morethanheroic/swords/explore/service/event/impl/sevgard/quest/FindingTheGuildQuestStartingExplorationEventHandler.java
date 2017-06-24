package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationAssignmentContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class FindingTheGuildQuestStartingExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 25;

    private static final int STARTER_STAGE = 0;

    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_ID = 1;
    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID = 2;

    private static final int HEALTH_LOST_ON_SHOOT = 5;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final VampireCalculator vampireCalculator;
    private final QuestDefinitionCache questDefinitionCache;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        throw new IllegalStateException("Handle explore called unexpectedly under quest: " + questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID).getName());
    }

    @Override
    public ExplorationResult handleInfo(final ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_1")
                                .newCustomMultiWayPath(() -> vampireCalculator.isVampire(explorationContext.getUserEntity()))
                                .isSuccess(
                                        explorationResultBuilder -> explorationResultBuilder
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_2")
                                                .newAttributeAttemptEntry(GeneralAttribute.DEXTERITY, 10)
                                                .isSuccess(
                                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_3")
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_5")
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_6")
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_7")
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_12")
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_13")
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_14")
                                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID)
                                                                .newContinueQuestEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID))
                                                                .build()
                                                )
                                                .isFailure(
                                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_4")
                                                                .newDamageEntry(HEALTH_LOST_ON_SHOOT)
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_5")
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_6")
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_7")
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_12")
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_13")
                                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_14")
                                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID)
                                                                .newContinueQuestEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID))
                                                                .build()
                                                )
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder -> explorationResultBuilder
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_8")
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_9")
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_10")
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_11")
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_12")
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_13")
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_14")
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID)
                                                .newContinueQuestEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID))
                                                .build()
                                )
                                .build()
                )
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_13")
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public boolean shouldAssign(ExplorationAssignmentContext explorationAssignmentContext) {
        return false;
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
