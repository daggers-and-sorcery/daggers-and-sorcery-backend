package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.service.CombatCalculator;
import com.morethanheroic.swords.combat.service.calc.drop.DropCalculator;
import com.morethanheroic.swords.combat.service.calc.drop.domain.DropSplitCalculationResult;
import com.morethanheroic.swords.combat.service.drop.DropAdder;
import com.morethanheroic.swords.combat.service.drop.DropTextCreator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.event.exception.IllegalExplorationEventStateException;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ReplyOption;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.monster.domain.DropAmountDefinition;
import com.morethanheroic.swords.monster.domain.DropDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ExplorationEvent
public class HermitInTheCaveExplorationEventHandler extends MultiStageExplorationEventHandler {

    private static final int EVENT_ID = 14;

    private static final int HERMIT_MONSTER_ID = 13;

    private static final int COMBAT_STAGE = 1;
    private static final int SEARCH_THE_CAVE_STAGE = 2;
    private static final int BACK_TO_THE_CITY_STAGE = 3;
    private static final int PICK_THE_LOCK = 4;
    private static final int SMASH_THE_LOCK = 5;

    private List<DropDefinition> chestDropDefinitions;

    private final ExplorationResultBuilderFactory explorationResultBuilderFactory;
    private final CombatCalculator combatCalculator;
    private final DropCalculator dropCalculator;
    private final DropAdder dropAdder;
    private final DropTextCreator dropTextCreator;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;

    public HermitInTheCaveExplorationEventHandler(ExplorationResultBuilderFactory explorationResultBuilderFactory, CombatCalculator combatCalculator, DropCalculator dropCalculator, DropAdder dropAdder, DropTextCreator dropTextCreator, ItemDefinitionCache itemDefinitionCache, UserBasicAttributeManipulator userBasicAttributeManipulator) {
        this.explorationResultBuilderFactory = explorationResultBuilderFactory;
        this.combatCalculator = combatCalculator;
        this.dropCalculator = dropCalculator;
        this.dropAdder = dropAdder;
        this.dropTextCreator = dropTextCreator;
        this.userBasicAttributeManipulator = userBasicAttributeManipulator;

        this.chestDropDefinitions = Lists.newArrayList(
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(99))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(1)
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(0.5)
                        .identified(false)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(112))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(1)
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(10)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(1))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(5)
                                        .maximumAmount(50)
                                        .build()
                        )
                        .chance(100)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(18))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(1)
                                        .maximumAmount(3)
                                        .build()
                        )
                        .chance(100)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(113))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(1)
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(4)
                        .identified(true)
                        .build()
        );
    }

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    @Override
    public int getId() {
        return EVENT_ID;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        return explorationResultBuilderFactory
                .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
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
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
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
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_11")
                    .resetExploration()
                    .build();
        } else if (stage == SEARCH_THE_CAVE_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newCustomLogicEntry(() -> userBasicAttributeManipulator.decreaseMovement(userEntity, 1))
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
            final List<Drop> chestDrops = dropCalculator.calculateDrops(chestDropDefinitions);

            final DropSplitCalculationResult dropSplitCalculationResult = dropCalculator.splitDrops(chestDrops, 50, true);

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newAttributeAttemptEntry(SkillAttribute.LOCKPICKING, 2)
                    .isSuccess((explorationResultBuilder) -> explorationResultBuilder
                            .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_7", dropTextCreator.listAsText(chestDrops))
                            .newCustomLogicEntry(() -> dropAdder.addDrops(userEntity, chestDrops))
                            .resetExploration()
                            .build()
                    )
                    .isFailure((explorationResultBuilder) -> explorationResultBuilder
                            .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_8")
                            .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_9", dropTextCreator.listAsText(dropSplitCalculationResult.getSuccessfulResult()), dropTextCreator.listAsText(dropSplitCalculationResult.getFailedResult()))
                            .newCustomLogicEntry(() -> dropAdder.addDrops(userEntity, dropSplitCalculationResult.getSuccessfulResult()))
                            .resetExploration()
                            .build()
                    )
                    .build();
        } else if (stage == SMASH_THE_LOCK) {
            final List<Drop> chestDrops = dropCalculator.calculateDrops(chestDropDefinitions);

            final DropSplitCalculationResult dropSplitCalculationResult = dropCalculator.splitDrops(chestDrops, 50, true);

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_9", dropTextCreator.listAsText(dropSplitCalculationResult.getSuccessfulResult()), dropTextCreator.listAsText(dropSplitCalculationResult.getFailedResult()))
                    .newCustomLogicEntry(() -> dropAdder.addDrops(userEntity, dropSplitCalculationResult.getSuccessfulResult()))
                    .resetExploration()
                    .build();
        }

        throw new IllegalExplorationEventStateException("Explore is not available on event: " + EVENT_ID + " at stage: " + stage);
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            if (combatCalculator.isCombatRunning(userEntity)) {
                return explorationResultBuilderFactory
                        .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                        .newMessageEntry("HERMIT_IN_THE_CAVE_EXPLORATION_EVENT_ENTRY_3")
                        .continueCombatEntry()
                        .build();
            } else {
                return explorationResultBuilderFactory
                        .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
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
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
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
