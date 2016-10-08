package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.service.calc.drop.DropCalculator;
import com.morethanheroic.swords.combat.service.drop.DropAdder;
import com.morethanheroic.swords.combat.service.drop.DropTextCreator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.event.exception.IllegalExplorationEventStateException;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ReplyOption;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.monster.domain.DropAmountDefinition;
import com.morethanheroic.swords.monster.domain.DropDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@ExplorationEvent
@RequiredArgsConstructor
public class UnderABoulderExplorationEventDefinition extends MultiStageExplorationEventHandler {

    private static final int EVENT_ID = 16;

    private static final int CHOOSE_PATH_STAGE = 1;
    private static final int RIGHT_PATH_STAGE = 2;
    private static final int LEFT_PATH_STAGE = 3;
    private static final int SMUGGLER_COMBAT_STAGE = 4;

    private static final int ROPE_ID = 119;
    private static final int TORCH_ID = 120;
    private static final int SILVER_COIN_ID = 49;

    private final ExplorationResultBuilderFactory explorationResultBuilderFactory;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;
    private final ExplorationEventDefinitionCache explorationEventDefinitionCache;
    private final DropCalculator dropCalculator;
    private final DropAdder dropAdder;
    private final DropTextCreator dropTextCreator;
    private final Random random;

    private List<DropDefinition> chestDropDefinitions;

    @PostConstruct
    public void setup() {
        this.chestDropDefinitions = Lists.newArrayList(
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(1))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(5)
                                        .maximumAmount(10)
                                        .build()
                        )
                        .chance(100)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(99))
                        .amount(
                                DropAmountDefinition.builder()
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(0.3)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(114))
                        .amount(
                                DropAmountDefinition.builder()
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(2)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(115))
                        .amount(
                                DropAmountDefinition.builder()
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(3)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(116))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(1)
                                        .maximumAmount(2)
                                        .build()
                        )
                        .chance(30)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(117))
                        .amount(
                                DropAmountDefinition.builder()
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(2)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(118))
                        .amount(
                                DropAmountDefinition.builder()
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(3)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(121))
                        .amount(
                                DropAmountDefinition.builder()
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(2)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(122))
                        .amount(
                                DropAmountDefinition.builder()
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(3)
                        .identified(true)
                        .build()
        );
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        return explorationResultBuilderFactory
                .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_2")
                .newCustomMultiWayPath(() -> {
                    final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

                    return inventoryEntity.hasItem(itemDefinitionCache.getDefinition(ROPE_ID)) && inventoryEntity.hasItem(itemDefinitionCache.getDefinition(TORCH_ID));
                })
                .isFailure((
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageBoxEntry("ITEMS_REQUIRED_EXPLORATION_EVENT_ENTRY_FAILURE", "1 Rope, 1 Torch")
                                .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_3")
                                .build()
                ))
                .isSuccess((
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageBoxEntry("ITEMS_REQUIRED_EXPLORATION_EVENT_ENTRY_SUCCESS", "1 Rope, 1 Torch")
                                .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_4")
                                .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_5")
                                .newOptionEntry(
                                        ReplyOption.builder()
                                                .message("UNDER_A_BOULDER_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                .stage(RIGHT_PATH_STAGE)
                                                .build(),
                                        ReplyOption.builder()
                                                .message("UNDER_A_BOULDER_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                                .stage(LEFT_PATH_STAGE)
                                                .build()
                                )
                                .setEventStage(EVENT_ID, CHOOSE_PATH_STAGE)
                                .build()
                ))
                .build();
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        if (stage == RIGHT_PATH_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_6")
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_7")
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_8")
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_9")
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_10")
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_11")
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_12")
                    .newCombatEntry(15, EVENT_ID, SMUGGLER_COMBAT_STAGE)
                    .build();
        } else if (stage == SMUGGLER_COMBAT_STAGE) {
            final List<Drop> chestDrops = dropCalculator.calculateDrops(chestDropDefinitions);
            final int silverCount = random.nextInt(2) + 1;

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_13", dropTextCreator.listAsText(chestDrops))
                    .newCustomLogicEntry(() -> dropAdder.addDrops(userEntity, chestDrops))
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_14")
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_15")
                    .newCustomLogicEntry(() -> inventoryEntityFactory.getEntity(userEntity.getId()).addItem(itemDefinitionCache.getDefinition(SILVER_COIN_ID), silverCount))
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_16", silverCount)
                    .resetExploration()
                    .build();
        } else if (stage == LEFT_PATH_STAGE) {
            final List<Drop> chestDrops = dropCalculator.calculateDrops(chestDropDefinitions);
            final int silverCount = random.nextInt(2) + 1;

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_17", dropTextCreator.listAsText(chestDrops))
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_18")
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_19")
                    .newCustomLogicEntry(() -> inventoryEntityFactory.getEntity(userEntity.getId()).addItem(itemDefinitionCache.getDefinition(SILVER_COIN_ID), silverCount))
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_20", silverCount)
                    .resetExploration()
                    .build();
        }

        throw new IllegalExplorationEventStateException("Explore is not available on event: " + EVENT_ID + " at stage: " + stage);
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        if (stage == CHOOSE_PATH_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_5")
                    .newOptionEntry(
                            ReplyOption.builder()
                                    .message("UNDER_A_BOULDER_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                    .stage(RIGHT_PATH_STAGE)
                                    .build(),
                            ReplyOption.builder()
                                    .message("UNDER_A_BOULDER_EXPLORATION_EVENT_QUESTION_REPLY_2")
                                    .stage(LEFT_PATH_STAGE)
                                    .build()
                    )
                    .build();
        } else if (stage == SMUGGLER_COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("UNDER_A_BOULDER_EXPLORATION_EVENT_ENTRY_12")
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
