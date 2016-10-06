package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilderFactory;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class UnderABoulderExplorationEventDefinition extends MultiStageExplorationEventDefinition {

    private final ExplorationResultBuilderFactory explorationResultBuilderFactory;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public int getId() {
        return 15;
    }

    @Override
    public ExplorationEventLocationType getLocation() {
        return ExplorationEventLocationType.WHISPERING_WOODS;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        return explorationResultBuilderFactory
                .newExplorationResultBuilder(userEntity)
                .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_2")
                .newCustomMultiWayPath(() -> {
                    final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

                    return inventoryEntity.hasItem(itemDefinitionCache.getDefinition(11122)) && inventoryEntity.hasItem(itemDefinitionCache.getDefinition(11122));
                })
                .isSuccess((
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_4")
                                .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_5")
                                .build()
                ))
                .isFailure((
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_3")
                                .build()
                ))
                .build();
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        return null;
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        return null;
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return false;
    }
}
