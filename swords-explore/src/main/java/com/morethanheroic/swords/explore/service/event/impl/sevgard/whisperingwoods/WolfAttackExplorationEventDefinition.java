package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

@ExplorationEvent
public class WolfAttackExplorationEventDefinition extends ExplorationEventDefinition {

    private static final int WOLF_MONSTER_ID = 7;

    @Autowired
    private ExplorationResultBuilder explorationResultBuilder;

    @Override
    public int getId() {
        return 11;
    }

    @Override
    public ExplorationEventLocationType getLocation() {
        return ExplorationEventLocationType.WHISPERING_WOODS;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        return explorationResultBuilder.initialize(userEntity)
                .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_2")
                .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_3")
                .newCombatEntry(WOLF_MONSTER_ID)
                .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_4")
                .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_5")
                .build();
    }
}
