package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilder;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@ExplorationEvent
public class WoodcuttersExplorationEventDefinition extends ExplorationEventDefinition {

    private static final int BRONZE_COIN_ID = 1;

    @Autowired
    private ExplorationResultBuilder explorationResultBuilder;

    @Autowired
    private Random random;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Override
    public int getId() {
        return 12;
    }

    @Override
    public ExplorationEventLocationType getLocation() {
        return ExplorationEventLocationType.WHISPERING_WOODS;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final int coinCount = rollRandomCoinReward();

        return explorationResultBuilder.initialize(userEntity)
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_2")
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_3")
                .newCustomLogicEntry(() -> {
                    final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);

                    inventoryEntity.addItem(BRONZE_COIN_ID, coinCount);
                })
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_4")
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_5", coinCount)
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_6", coinCount)
                .build();
    }

    private int rollRandomCoinReward() {
        return random.nextInt(8) + 6;
    }
}
