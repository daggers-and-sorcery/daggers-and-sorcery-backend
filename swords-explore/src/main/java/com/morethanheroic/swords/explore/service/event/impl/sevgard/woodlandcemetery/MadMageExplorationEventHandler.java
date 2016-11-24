package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class MadMageExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 19;

    private static final int STARTER_STAGE = 0;
    private static final int COMBAT_STAGE = 1;

    private static final int MAD_MAGE_MONSTER_ID = 19;
    private static final int MAD_MAGE_REWARD_CHEST_LOOT_ID = 99;

    private static final int TORCH_ID = 120;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("MAD_MAGE_EXPLORATION_EVENT_ENTRY_1")
                                .newMessageEntry("MAD_MAGE_EXPLORATION_EVENT_ENTRY_2")
                                .newMessageEntry("MAD_MAGE_EXPLORATION_EVENT_ENTRY_3")
                                .newMessageEntry("MAD_MAGE_EXPLORATION_EVENT_ENTRY_4")
                                .newCombatEntry(MAD_MAGE_MONSTER_ID, EVENT_ID, COMBAT_STAGE)
                                .build()
                )
                .addStage(COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("MAD_MAGE_EXPLORATION_EVENT_ENTRY_5")
                                .newHasItemMultiWayPath(explorationContext, TORCH_ID)
                                .isSuccess(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("MAD_MAGE_EXPLORATION_EVENT_ENTRY_6")
                                                .resetExploration()
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newLootEntry(MAD_MAGE_REWARD_CHEST_LOOT_ID, "MAD_MAGE_EXPLORATION_EVENT_ENTRY_7")
                                                .resetExploration()
                                                .build()
                                )
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
        return true;
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }
}
