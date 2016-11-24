package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;

import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class FeastOfAGhoulExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 18;

    private static final int STARTER_STAGE = 0;
    private static final int COMBAT_STAGE = 1;

    private static final int GHOUL_MONSTER_ID = 18;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FEAST_OF_A_GHOUL_EXPLORATION_EVENT_ENTRY_1")
                                .newMessageEntry("FEAST_OF_A_GHOUL_EXPLORATION_EVENT_ENTRY_2")
                                .newMessageEntry("FEAST_OF_A_GHOUL_EXPLORATION_EVENT_ENTRY_3")
                                .newMessageEntry("FEAST_OF_A_GHOUL_EXPLORATION_EVENT_ENTRY_4")
                                .newCombatEntry(GHOUL_MONSTER_ID, EVENT_ID, COMBAT_STAGE)
                                .build()
                )
                .addStage(COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FEAST_OF_A_GHOUL_EXPLORATION_EVENT_ENTRY_5")
                                .resetExploration()
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(final ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("FEAST_OF_A_GHOUL_EXPLORATION_EVENT_ENTRY_4")
                                .continueCombatEntry()
                                .build()
                )
                .runStage(explorationContext);
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
