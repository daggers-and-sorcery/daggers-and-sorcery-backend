package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class ZombiesExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 21;

    private static final int STARTER_STAGE = 0;
    private static final int FIRST_COMBAT_STAGE = 1;
    private static final int SECOND_COMBAT_STAGE = 2;

    private static final int ZOMBIE_MONSTER_ID = 20;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("ZOMBIES_EXPLORATION_EVENT_ENTRY_1")
                                .newMessageEntry("ZOMBIES_EXPLORATION_EVENT_ENTRY_2")
                                .newMessageEntry("ZOMBIES_EXPLORATION_EVENT_ENTRY_3")
                                .newMessageEntry("ZOMBIES_EXPLORATION_EVENT_ENTRY_4")
                                .newMessageEntry("ZOMBIES_EXPLORATION_EVENT_ENTRY_5")
                                .newCombatEntry(ZOMBIE_MONSTER_ID, EVENT_ID, FIRST_COMBAT_STAGE)
                                .build()
                )
                .addStage(FIRST_COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("ZOMBIES_EXPLORATION_EVENT_ENTRY_6")
                                .newCombatEntry(ZOMBIE_MONSTER_ID, EVENT_ID, SECOND_COMBAT_STAGE)
                                .build()
                )
                .addStage(SECOND_COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("ZOMBIES_EXPLORATION_EVENT_ENTRY_7")
                                .newMessageEntry("ZOMBIES_EXPLORATION_EVENT_ENTRY_8")
                                .resetExploration()
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(FIRST_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("ZOMBIES_EXPLORATION_EVENT_ENTRY_5")
                                .continueCombatEntry()
                                .build()
                )
                .addStage(SECOND_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("ZOMBIES_EXPLORATION_EVENT_ENTRY_6")
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

    @Override
    public int getId() {
        return EVENT_ID;
    }
}
