package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.service.CombatCalculator;
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
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.loot.domain.DropAmountDefinition;
import com.morethanheroic.swords.loot.domain.DropDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.morethanheroic.swords.attribute.domain.GeneralAttribute.PERCEPTION;

@ExplorationEvent
public class AbandonedHuntingLodgeExplorationEventHandler extends MultiStageExplorationEventHandler {

    private static final int EVENT_ID = 13;

    private static final int GNOLL_MONSTER_ID = 11;
    private static final int RAT_MONSTER_ID = 12;

    private static final int COMBAT_STAGE = 1;
    private static final int BACK_TO_THE_CITY_STAGE = 2;
    private static final int SEARCH_THE_LODGE_STAGE = 3;
    private static final int SECOND_COMBAT_STAGE = 4;

    private List<DropDefinition> chestDropDefinitions;

    private final ExplorationResultBuilderFactory explorationResultBuilderFactory;
    private final CombatCalculator combatCalculator;
    private final DropCalculator dropCalculator;
    private final DropAdder dropAdder;
    private final DropTextCreator dropTextCreator;

    public AbandonedHuntingLodgeExplorationEventHandler(final CombatCalculator combatCalculator,
                                                        final ExplorationResultBuilderFactory explorationResultBuilderFactory,
                                                        final ItemDefinitionCache itemDefinitionCache,
                                                        final DropCalculator dropCalculator,
                                                        final DropAdder dropAdder,
                                                        final DropTextCreator dropTextCreator) {
        this.combatCalculator = combatCalculator;
        this.explorationResultBuilderFactory = explorationResultBuilderFactory;
        this.dropCalculator = dropCalculator;
        this.dropAdder = dropAdder;
        this.dropTextCreator = dropTextCreator;

        chestDropDefinitions = Lists.newArrayList(
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
                        .item(itemDefinitionCache.getDefinition(1))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(30)
                                        .maximumAmount(70)
                                        .build()
                        )
                        .chance(100)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(36))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(1)
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(1)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(95))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(1)
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(1)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(23))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(1)
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(2)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(22))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(1)
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(2)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(63))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(1)
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(2)
                        .identified(true)
                        .build(),
                DropDefinition.builder()
                        .item(itemDefinitionCache.getDefinition(65))
                        .amount(
                                DropAmountDefinition.builder()
                                        .minimumAmount(1)
                                        .maximumAmount(1)
                                        .build()
                        )
                        .chance(2)
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
                .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_2")
                .newCombatEntry(GNOLL_MONSTER_ID, EVENT_ID, COMBAT_STAGE)
                .build();
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
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
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_4")
                    .build();
        } else if (stage == SEARCH_THE_LODGE_STAGE) {

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newAttributeAttemptEntry(PERCEPTION, 7)
                    .isSuccess((explorationResultBuilder) -> explorationResultBuilder
                            .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_6")
                            .newCombatEntry(RAT_MONSTER_ID, EVENT_ID, SECOND_COMBAT_STAGE)
                            .build()
                    )
                    .isFailure((explorationResultBuilder) -> explorationResultBuilder
                            .resetExploration()
                            .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_5")
                            .build()
                    )
                    .build();
        } else if (stage == SECOND_COMBAT_STAGE) {
            final List<Drop> chestDrops = dropCalculator.calculateDrops(chestDropDefinitions);

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newCustomLogicEntry(() -> dropAdder.addDrops(userEntity, chestDrops))
                    .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_7", dropTextCreator.listAsText(chestDrops))
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
                        .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_2")
                        .continueCombatEntry()
                        .build();
            } else {
                return explorationResultBuilderFactory
                        .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                        .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_3")
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
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("ABANDONED_HUNTING_LODGE_EXPLORATION_EVENT_ENTRY_6")
                    .continueCombatEntry()
                    .build();
        }

        throw new IllegalExplorationEventStateException("Info is not available on event: " + EVENT_ID + " at stage: " + stage);
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        if (stage == COMBAT_STAGE && nextStage == COMBAT_STAGE) {
            return true;
        }

        if (stage == COMBAT_STAGE && (nextStage == BACK_TO_THE_CITY_STAGE || nextStage == SEARCH_THE_LODGE_STAGE)) {
            return true;
        }

        if (stage == SEARCH_THE_LODGE_STAGE && nextStage == SECOND_COMBAT_STAGE) {
            return true;
        }

        if (stage == SECOND_COMBAT_STAGE && nextStage == SECOND_COMBAT_STAGE) {
            return true;
        }

        return false;
    }
}
