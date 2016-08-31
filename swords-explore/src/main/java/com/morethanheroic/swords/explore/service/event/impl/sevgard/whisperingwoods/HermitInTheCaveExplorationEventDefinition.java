package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.exception.IllegalExplorationEventStateException;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ReplyOption;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

@ExplorationEvent
public class HermitInTheCaveExplorationEventDefinition extends MultiStageExplorationEventDefinition {

    private static final int EVENT_ID = 14;

    private static final int HERMIT_MONSTER_ID = 222; //TODO

    private static final int COMBAT_STAGE = 1;
    private static final int SEARCH_THE_CAVE_STAGE = 2;
    private static final int BACK_TO_THE_CITY_STAGE = 3;

    @Autowired
    private ExplorationResultBuilderFactory explorationResultBuilderFactory;

    @Override
    public int getId() {
        return EVENT_ID;
    }

    @Override
    public ExplorationEventLocationType getLocation() {
        return ExplorationEventLocationType.WHISPERING_WOODS;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        return explorationResultBuilderFactory
                .newExplorationResultBuilder(userEntity)
                .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_2")
                .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_3")
                .newCombatEntry(HERMIT_MONSTER_ID, EVENT_ID, COMBAT_STAGE)
                .build();
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            userEntity.resetActiveExploration();

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_4")
                    .newOptionEntry(
                            ReplyOption.builder()
                                    .message("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                    .stage(SEARCH_THE_CAVE_STAGE)
                                    .build(),
                            ReplyOption.builder()
                                    .message("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                    .stage(BACK_TO_THE_CITY_STAGE)
                                    .build()
                    )
                    .build();
        } else if (stage == BACK_TO_THE_CITY_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .resetExploration(userEntity)
                    .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_11")
                    .build();
        } else if (stage == SEARCH_THE_CAVE_STAGE) {
            //TODO: lockpick event stb
        }

        throw new IllegalExplorationEventStateException("Explore is not available on event: " + EVENT_ID + " at stage: " + stage);
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
