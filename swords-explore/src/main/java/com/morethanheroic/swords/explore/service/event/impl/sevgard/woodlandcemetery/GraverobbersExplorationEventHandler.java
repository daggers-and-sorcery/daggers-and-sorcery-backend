package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.ReplyOption;

import lombok.RequiredArgsConstructor;

//@ExplorationEvent
@RequiredArgsConstructor
public class GraverobbersExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 20;

    private static final int STARTER_STAGE = 0;
    private static final int PAY_COINS_STAGE = 1;
    private static final int FIRST_COMBAT_STAGE = 2;
    private static final int SECOND_COMBAT_STAGE = 3;

    private static final int GRAVEROBBER_MONSTER_ID = 1;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                                                   .addStage(STARTER_STAGE,
                                                       explorationResultBuilder1 -> explorationResultBuilder1
                                                           .newMessageEntry("GRAVEROBBERS_EXPLORATION_EVENT_ENTRY_1")
                                                           .newMessageEntry("GRAVEROBBERS_EXPLORATION_EVENT_ENTRY_2")
                                                           .newMessageEntry("GRAVEROBBERS_EXPLORATION_EVENT_ENTRY_3")
                                                           .newMessageEntry("GRAVEROBBERS_EXPLORATION_EVENT_ENTRY_4")
                                                           .newMessageEntry("GRAVEROBBERS_EXPLORATION_EVENT_ENTRY_5")
                                                           .newOptionEntry(
                                                               ReplyOption.builder()
                                                                          .message("GRAVEROBBERS_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                                          .stage(PAY_COINS_STAGE)
                                                                          .build(),
                                                               ReplyOption.builder()
                                                                          .message("GRAVEROBBERS_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                                                          .stage(FIRST_COMBAT_STAGE)
                                                                          .build()
                                                           )
                                                           .setEventStage(EVENT_ID, STARTER_STAGE)
                                                           .build()
                                                   )
                                                   .addStage(PAY_COINS_STAGE,
                                                       explorationResultBuilder1 -> explorationResultBuilder1
                                                           .newMessageEntry("GRAVEROBBERS_EXPLORATION_EVENT_ENTRY_8")
                                                           //TODO: remove the money (what happens if he has no money??)
                                                           .resetExploration()
                                                           .build()
                                                       )
                                                   .addStage(FIRST_COMBAT_STAGE,
                                                       explorationResultBuilder1 -> explorationResultBuilder1
                                                           .newMessageEntry("GRAVEROBBERS_EXPLORATION_EVENT_ENTRY_9")
                                                           .newCombatEntry(GRAVEROBBER_MONSTER_ID, EVENT_ID, FIRST_COMBAT_STAGE)
                                                           .build()
                                                   )
                                                   .addStage(SECOND_COMBAT_STAGE,
                                                       explorationResultBuilder1 -> explorationResultBuilder1
                                                           .newMessageEntry("GRAVEROBBERS_EXPLORATION_EVENT_ENTRY_6")
                                                           .newCombatEntry(GRAVEROBBER_MONSTER_ID, EVENT_ID, SECOND_COMBAT_STAGE)
                                                           .build()
                                                   )
                                                   .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(final ExplorationContext explorationContext) {
        return null;
    }

    @Override
    public boolean isValidNextStageAtStage(final int stage, final int nextStage) {
        //TODO
        return true;
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }
}
