package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.ReplyOption;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;

import static com.morethanheroic.swords.attribute.domain.GeneralAttribute.DEXTERITY;

@ExplorationEvent
@RequiredArgsConstructor
public class ThePitExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 23;

    private static final int SKELETON_MONSTER_ID = 22;

    private static final int HEALT_LOSS_AMOUNT = 5;

    private static final int STARTER_STAGE = 0;
    private static final int FIRST_COMBAT_STAGE = 1;
    private static final int SECOND_COMBAT_STAGE = 2;
    private static final int GO_DEEPER_STAGE = 3;
    private static final int GO_TO_EXIT_STAGE = 4;
    private static final int THIRD_COMBAT_STAGE = 5;

    private static final int TORCH_ID = 120;
    private static final int BRONZE_COIN_ID = 1;

    private static final int SUCCESSFUL_CHEST_OPENING_RESULT_LOOT_ID = 7;
    private static final int FAILED_CHEST_OPENING_RESULT_LOOT_ID = 6;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        final UserEntity userEntity = explorationContext.getUserEntity();

        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_1")
                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_2")
                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_3")
                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_4")
                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_5")
                                .newCombatEntry(SKELETON_MONSTER_ID, EVENT_ID, FIRST_COMBAT_STAGE)
                                .build()
                )
                .addStage(FIRST_COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_6")
                                .newAttributeAttemptEntry(DEXTERITY, 8)
                                .isSuccess((explorationResultBuilder) -> explorationResultBuilder
                                        .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_8")
                                        .newCombatEntry(SKELETON_MONSTER_ID, EVENT_ID, SECOND_COMBAT_STAGE)
                                        .build()
                                )
                                .isFailure((explorationResultBuilder) -> explorationResultBuilder
                                        .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_7")
                                        .newCustomLogicEntry(() -> userBasicAttributeManipulator.increaseHealth(userEntity, HEALT_LOSS_AMOUNT))
                                        .newCombatEntry(SKELETON_MONSTER_ID, EVENT_ID, SECOND_COMBAT_STAGE)
                                        .build()
                                )
                                .build()
                )
                .addStage(SECOND_COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_9")
                                .newOptionEntry(
                                        ReplyOption.builder()
                                                .message("THE_PIT_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                .stage(GO_DEEPER_STAGE)
                                                .build(),
                                        ReplyOption.builder()
                                                .message("THE_PIT_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                                .stage(GO_TO_EXIT_STAGE)
                                                .build()
                                )
                                .build()
                )
                .addStage(GO_TO_EXIT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_10")
                                .resetExploration()
                                .build()
                )
                .addStage(GO_DEEPER_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newHasItemMultiWayPath(explorationContext, TORCH_ID)
                                .isSuccess(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_12")
                                                .newCombatEntry(SKELETON_MONSTER_ID, EVENT_ID, THIRD_COMBAT_STAGE)
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_11")
                                                .newCustomLogicEntry(() ->
                                                        inventoryEntityFactory.getEntity(userEntity).addItem(itemDefinitionCache.getDefinition(BRONZE_COIN_ID), 30)
                                                )
                                                .newMessageBoxEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_17")
                                                .resetExploration()
                                                .build()
                                )
                                .build()
                )
                .addStage(THIRD_COMBAT_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_13")
                                .newAttributeAttemptEntry(SkillAttribute.LOCKPICKING, 5)
                                .isSuccess((explorationResultBuilder) -> explorationResultBuilder
                                        .newLootEntry(SUCCESSFUL_CHEST_OPENING_RESULT_LOOT_ID, "THE_PIT_EXPLORATION_EVENT_ENTRY_16")
                                        .newCustomLogicEntry(() ->
                                                inventoryEntityFactory.getEntity(userEntity).addItem(itemDefinitionCache.getDefinition(BRONZE_COIN_ID), 30)
                                        )
                                        .newMessageBoxEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_17")
                                        .resetExploration()
                                        .build()
                                )
                                .isFailure((explorationResultBuilder) -> explorationResultBuilder
                                        .newLootEntry(FAILED_CHEST_OPENING_RESULT_LOOT_ID, "THE_PIT_EXPLORATION_EVENT_ENTRY_14")
                                        .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_15")
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
                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_5")
                                .continueCombatEntry()
                                .build()
                )
                .addStage(SECOND_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newIsCombatRunningMultiWayPath(explorationContext)
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_8")
                                                .continueCombatEntry()
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_9")
                                                .newOptionEntry(
                                                        ReplyOption.builder()
                                                                .message("THE_PIT_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                                .stage(GO_DEEPER_STAGE)
                                                                .build(),
                                                        ReplyOption.builder()
                                                                .message("THE_PIT_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                                                .stage(GO_TO_EXIT_STAGE)
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .addStage(THIRD_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("THE_PIT_EXPLORATION_EVENT_ENTRY_12")
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
