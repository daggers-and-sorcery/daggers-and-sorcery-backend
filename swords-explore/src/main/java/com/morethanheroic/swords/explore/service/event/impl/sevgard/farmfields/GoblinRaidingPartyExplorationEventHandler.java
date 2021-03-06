package com.morethanheroic.swords.explore.service.event.impl.sevgard.farmfields;

import com.morethanheroic.math.RandomCalculator;
import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.event.evaluator.CombatEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.CombatEventEntryEvaluatorResult;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilderFactory;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@ExplorationEvent
@RequiredArgsConstructor
public class GoblinRaidingPartyExplorationEventHandler extends MultiStageExplorationEventHandler {

    private static final int EVENT_ID = 7;

    private static final int GOBLIN_PIKEMAN_MONSTER_ID = 1;
    private static final int GOBLIN_SHAMAN_MONSTER_ID = 3;

    private static final int COMBAT_STAGE = 1;
    private static final int SECOND_COMBAT_STAGE = 2;

    private final ExplorationResultFactory explorationResultFactory;
    private final CombatEventEntryEvaluator combatEventEntryEvaluator;
    private final ExplorationResultBuilderFactory explorationResultBuilderFactory;
    private final ExplorationEventDefinitionCache explorationEventDefinitionCache;
    private final RandomCalculator randomCalculator;
    private final InventoryEntityFactory inventoryEntityFactory;

    private MonsterDefinition goblinPikeman;
    private MonsterDefinition goblinShaman;

    @PostConstruct
    private void initialize() {
        goblinPikeman = combatEventEntryEvaluator.convertMonsterIdToDefinition(GOBLIN_PIKEMAN_MONSTER_ID);
        goblinShaman = combatEventEntryEvaluator.convertMonsterIdToDefinition(GOBLIN_SHAMAN_MONSTER_ID);
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult(explorationEventDefinitionCache.getDefinition(EVENT_ID));

        explorationResult.addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You respond to a flyer posted on Sevgard's notice board. A farmer just outside the location is having a goblin problem. They are destroying his crops and slaughtering his livestock. He requires immediate assistance dealing with them, and he promises a reward. You take the flyer with you and follow the directions to the farmer's property.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You knock on the farmer's door with the flyer in hand. You greet the farmer and explain that you want to help. The farmer is thrilled and beckons you inside his home. A worn map of Farmfields rests on the kitchen table, and he points out a set of hills behind his farm, insisting the goblins are hiding there. During the night, they appear and create the worst racket while tearing up his fields. He alerted the location guard to his plight, but they have yet to kill the goblins. That responsibility has now shifted to you.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You promise the goblins will be dead by nightfall and leave the farm promptly. The hills are a stone throw away, and you even see smoke rising from a cook fire. These goblins have become arrogant. It is the middle of the day! You trek up a slope and notice a goblin encampment with bones strewn everywhere. It seems like they have been gorging themselves. With an enraged cry, you charge down the hill and attack a Goblin Pikeman.")
                        .build()
        );

        final CombatEventEntryEvaluatorResult combatResult = combatEventEntryEvaluator.calculateCombat(userEntity, goblinPikeman, CombatType.EXPLORE);

        explorationResult.addEventEntryResult(combatResult.getResult());

        if (!combatResult.getResult().isPlayerDead()) {
            userEntity.setActiveExploration(EVENT_ID, COMBAT_STAGE);
        }

        return explorationResult;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("GOBLIN_RAIDING_PARTY_EXPLORATION_EVENT_ENTRY_1")
                    .newCombatEntry(goblinShaman.getId(), EVENT_ID, SECOND_COMBAT_STAGE)
                    .build();
        } else if (stage == SECOND_COMBAT_STAGE) {
            final int resultCoins = randomCalculator.randomNumberBetween(6, 12);

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("GOBLIN_RAIDING_PARTY_EXPLORATION_EVENT_ENTRY_2")
                    .newCustomLogicEntry(() -> {
                        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

                        inventoryEntity.increaseMoneyAmount(MoneyType.MONEY, resultCoins);
                    })
                    .newMessageEntry("GOBLIN_RAIDING_PARTY_EXPLORATION_EVENT_ENTRY_4", resultCoins)
                    .resetExploration()
                    .build();
        }

        return explorationResultFactory.newExplorationResult(explorationEventDefinitionCache.getDefinition(EVENT_ID));
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("GOBLIN_RAIDING_PARTY_EXPLORATION_EVENT_ENTRY_3")
                    .continueCombatEntry()
                    .build();
        } else if (stage == SECOND_COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("GOBLIN_RAIDING_PARTY_EXPLORATION_EVENT_ENTRY_1")
                    .continueCombatEntry()
                    .build();
        }

        return explorationResultFactory.newExplorationResult(explorationEventDefinitionCache.getDefinition(EVENT_ID));
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return true;
    }
}
