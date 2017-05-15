package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.ReplyOption;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class FindingTheGuildExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 24;

    private static final int STARTER_STAGE = 0;
    private static final int ASK_FOR_HELP_STAGE = 1;
    private static final int COMBAT_STAGE = 2;
    private static final int ACCEPT_QUEST_STAGE = 3;
    private static final int DECLINE_QUEST_STAGE = 4;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final VampireCalculator vampireCalculator;
    private final QuestDefinitionCache questDefinitionCache;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_1")
                                .newCustomMultiWayPath(() -> vampireCalculator.isVampire(explorationContext.getUserEntity()))
                                .isSuccess(
                                        explorationResultBuilder -> explorationResultBuilder
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_2")
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
                                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_3")
                                                .newQuestEntry(questDefinitionCache.getDefinition(1), ACCEPT_QUEST_STAGE, DECLINE_QUEST_STAGE)
                                                //.newCustomLogicEntry(() -> witchhuntersGuildManipulator.unlockWitchhuntersGuildForUser(explorationContext.getUserEntity()))
                                                .build()
                                )
                                .build()
                )
                //TODO: Other stages!
                .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return null;
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
