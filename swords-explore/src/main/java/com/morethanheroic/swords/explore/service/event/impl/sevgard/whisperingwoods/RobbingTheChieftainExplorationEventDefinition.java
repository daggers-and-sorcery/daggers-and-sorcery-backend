package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

@ExplorationEvent
public class RobbingTheChieftainExplorationEventDefinition extends ExplorationEventDefinition {

    private static final int GOBLIN_GUARD_MONSTER_ID = 2;
    private static final int GOBLIN_CHIEFTAIN_MONSTER_ID = 5;

    @Autowired
    private ExplorationResultBuilder explorationResultBuilder;

    @Override
    public int getId() {
        return 10;
    }

    @Override
    public ExplorationEventLocationType getLocation() {
        return ExplorationEventLocationType.WHISPERING_WOODS;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        return explorationResultBuilder.initialize(userEntity)
                .newMessageEntry("ROBBING_THE_CHIEFTAIN_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("ROBBING_THE_CHIEFTAIN_EXPLORATION_EVENT_ENTRY_2")
                .newMessageEntry("ROBBING_THE_CHIEFTAIN_EXPLORATION_EVENT_ENTRY_3")
                .newAttributeProbeEntry(GeneralAttribute.DEXTERITY, 8)
                .isSuccess((explorationResultBuilder) -> explorationResultBuilder
                        .newMessageEntry("ROBBING_THE_CHIEFTAIN_EXPLORATION_EVENT_ENTRY_4")
                        .newCombatEntry(GOBLIN_CHIEFTAIN_MONSTER_ID)
                        .newCustomLogicEntry(() -> {
                            //TODO: Generate result to the reward chest
                        })
                        .newMessageEntry("ROBBING_THE_CHIEFTAIN_EXPLORATION_EVENT_ENTRY_5")
                        .build())
                .isFailure((explorationResultBuilder) -> explorationResultBuilder
                        .newMessageEntry("ROBBING_THE_CHIEFTAIN_EXPLORATION_EVENT_ENTRY_6")
                        .newCombatEntry(GOBLIN_GUARD_MONSTER_ID)
                        .newMessageEntry("ROBBING_THE_CHIEFTAIN_EXPLORATION_EVENT_ENTRY_7")
                        .build()
                )
                .build();
    }
}
