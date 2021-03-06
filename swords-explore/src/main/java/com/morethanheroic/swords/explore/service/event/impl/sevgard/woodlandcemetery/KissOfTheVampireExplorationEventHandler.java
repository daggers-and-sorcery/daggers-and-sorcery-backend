package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.ReplyOption;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.vampire.service.VampireCalculator;

import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class KissOfTheVampireExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 22;

    private static final int STARTER_STAGE = 0;
    private static final int KISSING_STAGE = 1;
    private static final int COMBAT_STAGE = 2;
    private static final int RUNNING_STAGE = 3;
    private static final int END_STAGE = 4;
    private static final int VAMPIRE_HEALING_STAGE = 5;
    private static final int VAMPIRE_RUNNING_STAGE = 6;

    private static final int VAMPIRE_MONSTER_ID = 21;

    private static final int HEALING_AMOUNT = 10;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;
    private final VampireCalculator vampireCalculator;

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        final UserEntity userEntity = explorationContext.getUserEntity();

        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newCustomMultiWayPath(() -> vampireCalculator.isVampire(userEntity))
                                .isSuccess(explorationResultBuilder -> explorationResultBuilder
                                        //The vampire side of the encounter
                                        .newMessageEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_6")
                                        .newOptionEntry(
                                                ReplyOption.builder()
                                                        .message("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_QUESTION_REPLY_4")
                                                        .stage(VAMPIRE_HEALING_STAGE)
                                                        .build(),
                                                ReplyOption.builder()
                                                        .message("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_QUESTION_REPLY_5")
                                                        .stage(VAMPIRE_RUNNING_STAGE)
                                                        .build()
                                        )
                                        .setEventStage(EVENT_ID, STARTER_STAGE)
                                        .build()
                                )
                                .isFailure(explorationResultBuilder -> explorationResultBuilder
                                        //The human side of the encounter
                                        .newMessageEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_1")
                                        .newOptionEntry(
                                                ReplyOption.builder()
                                                        .message("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                        .stage(KISSING_STAGE)
                                                        .build(),
                                                ReplyOption.builder()
                                                        .message("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                                        .stage(COMBAT_STAGE)
                                                        .build(),
                                                ReplyOption.builder()
                                                        .message("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_QUESTION_REPLY_3")
                                                        .stage(RUNNING_STAGE)
                                                        .build()
                                        )
                                        .setEventStage(EVENT_ID, STARTER_STAGE)
                                        .build()
                                )
                                .build()
                )
                .addStage(KISSING_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_2")
                                .newMessageBoxEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_10")
                                .newCustomLogicEntry(() -> vampireCalculator.setVampire(userEntity, true))
                                .resetExploration()
                                .build()
                )
                .addStage(RUNNING_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_5")
                                .resetExploration()
                                .build()
                )
                .addStage(COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_3")
                                .newCombatEntry(VAMPIRE_MONSTER_ID, EVENT_ID, END_STAGE)
                                .build()
                )
                .addStage(END_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_4")
                                .resetExploration()
                                .build()
                )
                .addStage(VAMPIRE_HEALING_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_8")
                                .newMessageBoxEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_9", HEALING_AMOUNT)
                                .newCustomLogicEntry(() -> userBasicAttributeManipulator.increaseHealth(userEntity, HEALING_AMOUNT))
                                .resetExploration()
                                .build()
                )
                .addStage(VAMPIRE_RUNNING_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_7")
                                //TODO: Add vampire cure quest entry here
                                .resetExploration()
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        final UserEntity userEntity = explorationContext.getUserEntity();

        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newCustomMultiWayPath(() -> vampireCalculator.isVampire(userEntity))
                                .isSuccess(explorationResultBuilder -> explorationResultBuilder
                                        //The vampire side of the encounter
                                        .newMessageEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_6")
                                        .newOptionEntry(
                                                ReplyOption.builder()
                                                        .message("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_QUESTION_REPLY_4")
                                                        .stage(VAMPIRE_HEALING_STAGE)
                                                        .build(),
                                                ReplyOption.builder()
                                                        .message("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_QUESTION_REPLY_5")
                                                        .stage(VAMPIRE_RUNNING_STAGE)
                                                        .build()
                                        )
                                        .setEventStage(EVENT_ID, STARTER_STAGE)
                                        .build()
                                )
                                .isFailure(explorationResultBuilder -> explorationResultBuilder
                                        //The human side of the encounter
                                        .newMessageEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_1")
                                        .newOptionEntry(
                                                ReplyOption.builder()
                                                        .message("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                        .stage(KISSING_STAGE)
                                                        .build(),
                                                ReplyOption.builder()
                                                        .message("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                                        .stage(COMBAT_STAGE)
                                                        .build(),
                                                ReplyOption.builder()
                                                        .message("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_QUESTION_REPLY_3")
                                                        .stage(RUNNING_STAGE)
                                                        .build()
                                        )
                                        .setEventStage(EVENT_ID, STARTER_STAGE)
                                        .build()
                                )
                                .build()
                )
                .addStage(END_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("KISS_OF_A_VAMPIRE_EXPLORATION_EVENT_ENTRY_3")
                                .continueCombatEntry()
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        //TODO
        return true;
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }
}
