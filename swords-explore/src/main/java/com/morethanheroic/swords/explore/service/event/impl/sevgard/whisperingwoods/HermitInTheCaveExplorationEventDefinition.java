package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
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
public class HermitInTheCaveExplorationEventDefinition extends MultiStageExplorationEventDefinition {

    private static final int EVENT_ID = 14;

    private static final int HERMIT_MONSTER_ID = 13; //TODO

    private static final int COMBAT_STAGE = 1;
    private static final int SEARCH_THE_CAVE_STAGE = 2;
    private static final int BACK_TO_THE_CITY_STAGE = 3;
    private static final int PICK_THE_LOCK = 4;
    private static final int SMASH_THE_LOCK = 5;

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
                .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_2")
                .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_3")
                .newCombatEntry(HERMIT_MONSTER_ID, EVENT_ID, COMBAT_STAGE)
                .build();
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
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
                    .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_11")
                    .resetExploration(userEntity)
                    .build();
        } else if (stage == SEARCH_THE_CAVE_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_5")
                    .newOptionEntry(
                            ReplyOption.builder()
                                    .message("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_QUESTION_REPLY_3")
                                    .stage(PICK_THE_LOCK)
                                    .build(),
                            ReplyOption.builder()
                                    .message("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_QUESTION_REPLY_4")
                                    .stage(SMASH_THE_LOCK)
                                    .build()
                    )
                    .build();
        } else if (stage == PICK_THE_LOCK) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newAttributeProbeEntry(SkillAttribute.LOCKPICKING, 2)
                    .isSuccess((explorationResultBuilder) -> explorationResultBuilder
                            .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_7")
                            .newCustomLogicEntry(() -> System.out.print("add the drops"))
                            .resetExploration(userEntity)
                            .build()
                    )
                    .isFailure((explorationResultBuilder) -> explorationResultBuilder
                            .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_8")
                            .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_9")
                            .newCustomLogicEntry(() -> System.out.print("add the drops"))
                            .resetExploration(userEntity)
                            .build()
                    )
                    .build();
        } else if (stage == SMASH_THE_LOCK) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_9")
                    .newCustomLogicEntry(() -> System.out.print("add the drops"))
                    .resetExploration(userEntity)
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
                        .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_3")
                        .continueCombatEntry()
                        .build();
            } else {
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
            }
        } else if (stage == SEARCH_THE_CAVE_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_5")
                    .newOptionEntry(
                            ReplyOption.builder()
                                    .message("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_QUESTION_REPLY_3")
                                    .stage(PICK_THE_LOCK)
                                    .build(),
                            ReplyOption.builder()
                                    .message("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_QUESTION_REPLY_4")
                                    .stage(SMASH_THE_LOCK)
                                    .build()
                    )
                    .build();
        }

        throw new IllegalExplorationEventStateException("Info is not available on event: " + EVENT_ID + " at stage: " + stage);
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        //TODO:
        return true;
    }
}
