package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
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

@ExplorationEvent
@RequiredArgsConstructor
public class ChasingTheGoblinsExplorationEventDefinition extends MultiStageExplorationEventDefinition {

    private static final int EVENT_ID = 15;

    private static final int GOBLIN_SEPARATIST_MONSTER_ID = 14;
    private static final int GOBLIN_GUARD_MONSTER_ID = 2;

    private static final int STARTING_STAGE = 0;
    private static final int KILL_THE_GOBLIN_STAGE = 1;
    private static final int FOLLOW_THE_GOBLIN_STAGE = 2;
    private static final int FIRST_COMBAT_STAGE = 3;
    private static final int SECOND_COMBAT_STAGE = 4;
    private static final int THIRD_COMBAT_STAGE = 5;
    private static final int GO_HOME_STAGE = 6;
    private static final int ATTACK_THE_SEPARATIST_STAGE = 7;
    private static final int FOURTH_COMBAT_STAGE = 8;


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
                .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_2")
                .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_3")
                .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_4")
                .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_5")
                .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_6")
                .newOptionEntry(
                        ReplyOption.builder()
                                .message("CHASING_THE_GOBLINS_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                .stage(KILL_THE_GOBLIN_STAGE)
                                .build(),
                        ReplyOption.builder()
                                .message("CHASING_THE_GOBLINS_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                .stage(FOLLOW_THE_GOBLIN_STAGE)
                                .build()
                )
                .setEventStage(EVENT_ID, STARTING_STAGE)
                .build();
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        if (stage == KILL_THE_GOBLIN_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    //TODO: attack text missing
                    .newCombatEntry(GOBLIN_SEPARATIST_MONSTER_ID, EVENT_ID, FIRST_COMBAT_STAGE)
                    .build();
        } else if (stage == FIRST_COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_7")
                    .resetExploration()
                    .build();
        } else if (stage == FOLLOW_THE_GOBLIN_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_8")
                    .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_9")
                    .newCombatEntry(GOBLIN_GUARD_MONSTER_ID, EVENT_ID, SECOND_COMBAT_STAGE)
                    .build();
        } else if (stage == SECOND_COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_10")
                    .newCombatEntry(GOBLIN_GUARD_MONSTER_ID, EVENT_ID, THIRD_COMBAT_STAGE)
                    .build();
        } else if (stage == THIRD_COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_11")
                    .newOptionEntry(
                            ReplyOption.builder()
                                    .message("CHASING_THE_GOBLINS_EXPLORATION_EVENT_QUESTION_REPLY_3")
                                    .stage(GO_HOME_STAGE)
                                    .build(),
                            ReplyOption.builder()
                                    .message("CHASING_THE_GOBLINS_EXPLORATION_EVENT_QUESTION_REPLY_4")
                                    .stage(ATTACK_THE_SEPARATIST_STAGE)
                                    .build()
                    )
                    .build();
        } else if (stage == GO_HOME_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_12")
                    .resetExploration()
                    .build();
        } else if (stage == ATTACK_THE_SEPARATIST_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_13")
                    .newAttributeAttemptEntry(GeneralAttribute.SWIFTNESS, 8)
                    .isSuccess((explorationResultBuilder) -> explorationResultBuilder
                            .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_15")
                            .newCombatEntry(GOBLIN_SEPARATIST_MONSTER_ID, EVENT_ID, FOURTH_COMBAT_STAGE)
                            .build()
                    )
                    .isFailure((explorationResultBuilder) -> explorationResultBuilder
                            .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_14")
                            .resetExploration()
                            .build()
                    )
                    .build();
        } else if (stage == FOURTH_COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_16")
                    .resetExploration()
                    .build();
        }

        throw new IllegalExplorationEventStateException("Explore is not available on event: " + EVENT_ID + " at stage: " + stage);
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        if (stage == STARTING_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_6")
                    .newOptionEntry(
                            ReplyOption.builder()
                                    .message("CHASING_THE_GOBLINS_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                    .stage(KILL_THE_GOBLIN_STAGE)
                                    .build(),
                            ReplyOption.builder()
                                    .message("CHASING_THE_GOBLINS_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                    .stage(FOLLOW_THE_GOBLIN_STAGE)
                                    .build()
                    )
                    .setEventStage(EVENT_ID, STARTING_STAGE)
                    .build();
        } else if (stage == FIRST_COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    //TODO: add the text
                    .continueCombatEntry()
                    .build();
        } else if (stage == SECOND_COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_9")
                    .continueCombatEntry()
                    .build();
        } else if (stage == THIRD_COMBAT_STAGE) {
            if (combatCalculator.isCombatRunning(userEntity)) {
                return explorationResultBuilderFactory
                        .newExplorationResultBuilder(userEntity)
                        .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_10")
                        .continueCombatEntry()
                        .build();
            } else {
                return explorationResultBuilderFactory
                        .newExplorationResultBuilder(userEntity)
                        .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_11")
                        .newOptionEntry(
                                ReplyOption.builder()
                                        .message("CHASING_THE_GOBLINS_EXPLORATION_EVENT_QUESTION_REPLY_3")
                                        .stage(GO_HOME_STAGE)
                                        .build(),
                                ReplyOption.builder()
                                        .message("CHASING_THE_GOBLINS_EXPLORATION_EVENT_QUESTION_REPLY_4")
                                        .stage(ATTACK_THE_SEPARATIST_STAGE)
                                        .build()
                        )
                        .build();
            }
        } else if (stage == FOURTH_COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("CHASING_THE_GOBLINS_EXPLORATION_EVENT_ENTRY_15")
                    .continueCombatEntry()
                    .build();
        }

        throw new IllegalExplorationEventStateException("Explore is not available on event: " + EVENT_ID + " at stage: " + stage);
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        //TODO
        return true;
    }
}
