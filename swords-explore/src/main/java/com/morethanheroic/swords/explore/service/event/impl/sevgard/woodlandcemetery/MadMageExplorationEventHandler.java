package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;

import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class MadMageExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 19;

    private static final int STARTER_STAGE = 0;
    private static final int COMBAT_STAGE = 1;

    private static final int MAD_MAGE_MONSTER_ID = 16;

    private static final int TORCH_ID = 120;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;

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
                       //TODO
                   )
                   .isFailure(
                       //TODO
                   )
                   .resetExploration()
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
