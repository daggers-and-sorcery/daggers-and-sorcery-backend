package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.*;
import com.morethanheroic.swords.quest.service.QuestStateCalculator;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class FindingTheGuildExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 24;

    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_ID = 1;
    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_STARTED_STATE_ID = 1;

    private static final int YIP_THE_VAMPIRE_HUNTER = 26;

    private static final int STARTER_STAGE = 0;
    private static final int ASK_FOR_HELP_STAGE = 1;
    private static final int COMBAT_STAGE = 2;
    private static final int ACCEPT_QUEST_STAGE = 3;
    private static final int DECLINE_QUEST_STAGE = 4;
    private static final int AFTER_COMBAT_STAGE = 5;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final VampireCalculator vampireCalculator;
    private final QuestDefinitionCache questDefinitionCache;
    private final QuestStateCalculator questStateCalculator;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newCustomMultiWayPath(() -> vampireCalculator.isVampire(explorationContext.getUserEntity()))
                                .isSuccess(
                                        explorationResultBuilder -> explorationResultBuilder
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_4")
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_5")
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_6")
                                                .newOptionEntry(
                                                        ReplyOption.builder()
                                                                .message("FINDING_THE_GUILD_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                                .stage(ASK_FOR_HELP_STAGE)
                                                                .build(),
                                                        ReplyOption.builder()
                                                                .message("FINDING_THE_GUILD_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                                                .stage(COMBAT_STAGE)
                                                                .build()
                                                )
                                                .setEventStage(EVENT_ID, STARTER_STAGE)
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder -> explorationResultBuilder
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_1")
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_2")
                                                .newQuestDialogEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), ACCEPT_QUEST_STAGE, DECLINE_QUEST_STAGE)
                                                .setEventStage(EVENT_ID, STARTER_STAGE)
                                                .build()
                                )
                                .build()
                )
                .addStage(COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_7")
                                .newCombatEntry(YIP_THE_VAMPIRE_HUNTER, EVENT_ID, AFTER_COMBAT_STAGE)
                                .build()
                )
                .addStage(AFTER_COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_8")
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_9")
                                .resetExploration()
                                .build()
                )
                .addStage(ASK_FOR_HELP_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newQuestDialogEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), ACCEPT_QUEST_STAGE, DECLINE_QUEST_STAGE)
                                .build()
                )
                .addStage(ACCEPT_QUEST_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_3")
                                .newAcceptQuestEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID))
                                .resetExploration()
                                .build()
                )
                .addStage(DECLINE_QUEST_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_3")
                                .resetExploration()
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newCustomMultiWayPath(() -> vampireCalculator.isVampire(explorationContext.getUserEntity()))
                                .isSuccess(
                                        explorationResultBuilder -> explorationResultBuilder
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_4")
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_5")
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_6")
                                                .newOptionEntry(
                                                        ReplyOption.builder()
                                                                .message("FINDING_THE_GUILD_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                                .stage(ASK_FOR_HELP_STAGE)
                                                                .build(),
                                                        ReplyOption.builder()
                                                                .message("FINDING_THE_GUILD_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                                                .stage(COMBAT_STAGE)
                                                                .build()
                                                )
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder -> explorationResultBuilder
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_1")
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_2")
                                                .newQuestDialogEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), ACCEPT_QUEST_STAGE, DECLINE_QUEST_STAGE)
                                                .build()
                                )
                                .build()
                )
                .addStage(ASK_FOR_HELP_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newQuestDialogEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), ACCEPT_QUEST_STAGE, DECLINE_QUEST_STAGE)
                                .build()
                )
                .addStage(AFTER_COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_7")
                                .continueCombatEntry()
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public boolean shouldAssign(ExplorationAssignmentContext explorationAssignmentContext) {
        return questStateCalculator.getQuestStage(explorationAssignmentContext.getUserEntity(), questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID))
                == WITCHHUNTER_GUILD_JOIN_QUEST_STARTED_STATE_ID;
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return true;
    }
}
