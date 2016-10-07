package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilderFactory;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@ExplorationEvent
public class WoodcuttersExplorationEventHandler extends ExplorationEventHandler {

    private static final int BRONZE_COIN_ID = 1;

    @Autowired
    private ExplorationResultBuilderFactory explorationResultBuilderFactory;

    @Autowired
    private Random random;

    @Autowired
    private InventoryEntityFactory inventoryEntityFactory;

    @Override
    public int getId() {
        return 12;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final int coinCount = rollRandomCoinReward();

        return explorationResultBuilderFactory
                .newExplorationResultBuilder(userEntity)
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_2")
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_3")
                .newCustomLogicEntry(() -> inventoryEntityFactory.getEntity(userEntity.getId()).addItem(BRONZE_COIN_ID, coinCount))
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_4")
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_5", coinCount)
                .newMessageEntry("WOODCUTTERS_EXPLORATION_EVENT_ENTRY_6", coinCount)
                .build();
    }

    private int rollRandomCoinReward() {
        return random.nextInt(8) + 6;
    }
}
