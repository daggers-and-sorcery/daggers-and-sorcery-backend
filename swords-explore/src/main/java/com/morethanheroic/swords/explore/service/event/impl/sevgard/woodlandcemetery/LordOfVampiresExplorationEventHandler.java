package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.ReplyOption;
import com.morethanheroic.swords.explore.service.event.newevent.condition.ConditionFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class LordOfVampiresExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 34;

    private static final int STARTER_STAGE = 0;
    private static final int FIRST_COMBAT_STAGE = 1;
    private static final int SECOND_COMBAT_STAGE = 2;
    private static final int GO_HOME_STAGE = 3;
    private static final int ATTACK_THE_VAMPIRE_STAGE = 4;
    private static final int THIRD_COMBAT_STAGE = 5;
    private static final int FOURTH_COMBAT_STAGE = 6;

    private static final int VAMPIRE_SCOUT_ID = 123;
    private static final int VAMPIRE_WARRIOR_ID = 123;
    private static final int VAMPIRE_LORD_ID = 123;

    private static final int COIN_OF_DRAKKAR_ID = 1234;
    private static final int AMULET_PIECE_ID = 1234;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final ItemDefinitionCache itemDefinitionCache;
    private final ConditionFactory conditionFactory;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_1")
                                .newAttributeAttemptEntry(GeneralAttribute.DEXTERITY, 10)
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_2")
                                                .newCombatEntry(VAMPIRE_SCOUT_ID, EVENT_ID, FIRST_COMBAT_STAGE)
                                                .build()
                                )
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_5")
                                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_6")
                                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_7")
                                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_8")
                                                .newOptionEntry(
                                                        ReplyOption.builder()
                                                                .message("LORD_OF_VAMPIRES_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                                .stage(ATTACK_THE_VAMPIRE_STAGE)
                                                                .build(),
                                                        ReplyOption.builder()
                                                                .message("LORD_OF_VAMPIRES_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                                                .stage(GO_HOME_STAGE)
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .addStage(FIRST_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_3")
                                .newCombatEntry(VAMPIRE_SCOUT_ID, EVENT_ID, SECOND_COMBAT_STAGE)
                                .build()
                )
                .addStage(SECOND_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_4")
                                .resetExploration()
                                .build()
                )
                .addStage(GO_HOME_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_9")
                                .resetExploration()
                                .build()
                )
                .addStage(ATTACK_THE_VAMPIRE_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_10")
                                .newCombatEntry(VAMPIRE_WARRIOR_ID, EVENT_ID, THIRD_COMBAT_STAGE)
                                .build()
                )
                .addStage(THIRD_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_11")
                                .newCombatEntry(VAMPIRE_LORD_ID, EVENT_ID, FOURTH_COMBAT_STAGE)
                                .build()
                )
                .addStage(FOURTH_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_12")
                                .newAttributeAttemptEntry(SkillAttribute.LOCKPICKING, 10)
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_14")
                                                .newConditionalMultiWayPath(explorationContext,
                                                        conditionFactory.newConditionBuilder()
                                                                .newItemCondition(itemDefinitionCache.getDefinition(COIN_OF_DRAKKAR_ID), 3)
                                                                .build()
                                                )
                                                .isFailure(
                                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_15")
                                                                .resetExploration()
                                                                .build()
                                                )
                                                .isSuccess(
                                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_16")
                                                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_17")
                                                                .newAddItemEntry(itemDefinitionCache.getDefinition(AMULET_PIECE_ID))
                                                                .resetExploration()
                                                                .build()
                                                )
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_13")
                                                .resetExploration()
                                                .build()
                                )
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(FIRST_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_1")
                                .continueCombatEntry()
                                .build()
                )
                .addStage(SECOND_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_3")
                                .continueCombatEntry()
                                .build()
                )
                .addStage(THIRD_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_10")
                                .continueCombatEntry()
                                .build()
                )
                .addStage(FOURTH_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("LORD_OF_VAMPIRES_EXPLORATION_EVENT_ENTRY_11")
                                .continueCombatEntry()
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return true;
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }
}
