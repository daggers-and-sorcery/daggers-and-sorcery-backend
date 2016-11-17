package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.ReplyOption;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class TheGoblinKingExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 17;

    private static final int STARTER_STAGE = 0;
    private static final int CHOOSE_PATH_STAGE = 1;
    private static final int GO_HOME_STAGE = 2;
    private static final int ATTACK_THE_GOBLIN_STAGE = 3;
    private static final int COMBAT_STAGE = 4;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public int getId() {
        return EVENT_ID;
    }

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("GOBLIN_KING_EXPLORATION_EVENT_ENTRY_1")
                                .newMessageEntry("GOBLIN_KING_EXPLORATION_EVENT_ENTRY_2")
                                .newMessageEntry("GOBLIN_KING_EXPLORATION_EVENT_ENTRY_3")
                                .newOptionEntry(
                                        ReplyOption.builder()
                                                .message("GOBLIN_KING_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                .stage(ATTACK_THE_GOBLIN_STAGE)
                                                .build(),
                                        ReplyOption.builder()
                                                .message("GOBLIN_KING_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                                .stage(GO_HOME_STAGE)
                                                .build()
                                )
                                .setEventStage(EVENT_ID, CHOOSE_PATH_STAGE)
                                .build()
                )
                .addStage(ATTACK_THE_GOBLIN_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("GOBLIN_KING_EXPLORATION_EVENT_ENTRY_5")
                                .newCombatEntry(16, EVENT_ID, COMBAT_STAGE)
                                .build()
                )
                .addStage(
                        GO_HOME_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("GOBLIN_KING_EXPLORATION_EVENT_ENTRY_4")
                                .resetExploration()
                                .build()
                )
                .addStage(
                        COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
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
                                .newMessageEntry("GOBLIN_KING_EXPLORATION_EVENT_ENTRY_3")
                                .newOptionEntry(
                                        ReplyOption.builder()
                                                .message("GOBLIN_KING_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                .stage(ATTACK_THE_GOBLIN_STAGE)
                                                .build(),
                                        ReplyOption.builder()
                                                .message("GOBLIN_KING_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                                .stage(GO_HOME_STAGE)
                                                .build()
                                )
                                .setEventStage(EVENT_ID, CHOOSE_PATH_STAGE)
                                .build()
                ).addStage(COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("GOBLIN_KING_EXPLORATION_EVENT_ENTRY_3")
                                .continueCombatEntry()
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        //TODO
        return true;
    }
}
