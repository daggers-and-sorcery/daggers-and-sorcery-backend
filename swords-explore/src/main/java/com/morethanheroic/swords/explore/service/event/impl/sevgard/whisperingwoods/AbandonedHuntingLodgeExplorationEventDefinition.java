package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.combat.service.CombatCalculator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.exception.IllegalExplorationEventStateException;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ReplyOption;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;

import static com.morethanheroic.swords.attribute.domain.GeneralAttribute.PERCEPTION;

@ExplorationEvent
@RequiredArgsConstructor
public class AbandonedHuntingLodgeExplorationEventDefinition extends MultiStageExplorationEventDefinition {

    private static final int EVENT_ID = 13;

    private static final int GNOLL_MONSTER_ID = 11;
    private static final int RAT_MONSTER_ID = 12;

    private static final int COMBAT_STAGE = 1;
    private static final int BACK_TO_THE_CITY_STAGE = 2;
    private static final int SEARCH_THE_LODGE_STAGE = 3;
    private static final int SECOND_COMBAT_STAGE = 4;

    private final ExplorationResultBuilderFactory explorationResultBuilderFactory;
    private final CombatCalculator combatCalculator;

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
                .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_2")
                .newCombatEntry(GNOLL_MONSTER_ID, EVENT_ID, COMBAT_STAGE)
                .build();
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_3")
                    .newOptionEntry(
                            ReplyOption.builder()
                                    .message("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                    .stage(BACK_TO_THE_CITY_STAGE)
                                    .build(),
                            ReplyOption.builder()
                                    .message("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                    .stage(SEARCH_THE_LODGE_STAGE)
                                    .build()
                    )
                    .build();
        } else if (stage == BACK_TO_THE_CITY_STAGE) {
            userEntity.resetActiveExploration();

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_4")
                    .build();
        } else if (stage == SEARCH_THE_LODGE_STAGE) {

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newAttributeProbeEntry(PERCEPTION, 8)
                    .isSuccess((explorationResultBuilder) -> explorationResultBuilder
                            .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_5")
                            .build()
                    )
                    .isFailure((explorationResultBuilder) -> explorationResultBuilder
                            .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_6")
                            .newCombatEntry(RAT_MONSTER_ID, EVENT_ID, SECOND_COMBAT_STAGE)
                            .build()
                    )
                    .build();
        } else if (stage == SECOND_COMBAT_STAGE) {
            userEntity.resetActiveExploration();

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_7")
                    .build();
        }

        throw new IllegalExplorationEventStateException("Explore is not available on event: " + EVENT_ID + " at stage: " + stage);
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            if (combatCalculator.isCombatRunning(userEntity)) {
                return explorationResultBuilderFactory
                        .newExplorationResultBuilder(userEntity)
                        .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_2")
                        .continueCombatEntry()
                        .build();
            } else {
                return explorationResultBuilderFactory
                        .newExplorationResultBuilder(userEntity)
                        .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_3")
                        .newOptionEntry(
                                ReplyOption.builder()
                                        .message("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                        .stage(BACK_TO_THE_CITY_STAGE)
                                        .build(),
                                ReplyOption.builder()
                                        .message("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                        .stage(SEARCH_THE_LODGE_STAGE)
                                        .build()
                        )
                        .build();
            }
        } else if (stage == SECOND_COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_6")
                    .continueCombatEntry()
                    .build();
        }

        throw new IllegalExplorationEventStateException("Info is not available on event: " + EVENT_ID + " at stage: " + stage);
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        if (stage == COMBAT_STAGE && (nextStage == BACK_TO_THE_CITY_STAGE || nextStage == SEARCH_THE_LODGE_STAGE)) {
            return true;
        }

        if (stage == SEARCH_THE_LODGE_STAGE && nextStage == SECOND_COMBAT_STAGE) {
            return true;
        }

        return false;
    }
}
